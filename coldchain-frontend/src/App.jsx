import { useState, useEffect } from 'react';
import { Chart as ChartJS, ArcElement, Tooltip, Legend, Title, ResponsiveContainer } from 'chart.js';
import { Pie } from 'react-chartjs-2';
import {
  LayoutDashboard,
  Package,
  PlusCircle,
  RefreshCw,
  Search,
  Settings,
  User,
  Bell,
  Thermometer,
  ShieldCheck,
  Clock,
  X,
  CreditCard,
  ChevronRight,
  TrendingUp,
  AlertTriangle
} from 'lucide-react';

// Register ChartJS components
ChartJS.register(ArcElement, Tooltip, Legend, Title);

function App() {
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const [newBatch, setNewBatch] = useState({
    batchNumber: '',
    vaccineType: '',
    expiryDate: ''
  });

  const fetchData = async () => {
    setLoading(true);
    try {
      const response = await fetch('/api/vaccines');
      if (response.ok) {
        const result = await response.json();
        setData(result);
      } else {
        // Fallback to mock data if API fails or is empty for demo purposes
        setData([
          { batchNumber: 'VAC-001', vaccineType: 'Pfizer', expiryDate: '2025-12-31', storageUnit: { unitId: 'ST-01' } },
          { batchNumber: 'VAC-002', vaccineType: 'Moderna', expiryDate: '2025-10-15', storageUnit: { unitId: 'ST-02' } },
          { batchNumber: 'VAC-003', vaccineType: 'Covaxin', expiryDate: '2026-01-20', storageUnit: null },
        ]);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleSaveBatch = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('/api/vaccines', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newBatch),
      });
      if (response.ok) {
        setShowModal(false);
        setNewBatch({ batchNumber: '', vaccineType: '', expiryDate: '' });
        fetchData();
      } else {
        alert('Failed to save batch');
      }
    } catch (error) {
      console.error('Error saving data:', error);
    }
  };

  const getChartData = () => {
    const counts = data.reduce((acc, item) => {
      acc[item.vaccineType] = (acc[item.vaccineType] || 0) + 1;
      return acc;
    }, {});

    return {
      labels: Object.keys(counts),
      datasets: [{
        data: Object.values(counts),
        backgroundColor: [
          'rgba(59, 130, 246, 0.8)',
          'rgba(168, 85, 247, 0.8)',
          'rgba(236, 72, 153, 0.8)',
          'rgba(245, 158, 11, 0.8)',
        ],
        hoverOffset: 15,
        borderWidth: 0,
      }],
    };
  };

  return (
    <div className="flex h-screen bg-transparent text-slate-200 overflow-hidden font-['Outfit']">

      {/* Sidebar */}
      <aside className="w-64 glass-card border-r border-white/10 hidden lg:flex flex-col p-6">
        <div className="flex items-center gap-3 mb-10 px-2">
          <div className="w-10 h-10 bg-blue-600 rounded-xl flex items-center justify-center shadow-lg shadow-blue-500/20">
            <ShieldCheck className="text-white w-6 h-6" />
          </div>
          <h1 className="text-xl font-bold tracking-tight text-white italic">
            Cold<span className="gradient-text">Chain</span>
          </h1>
        </div>

        <nav className="flex-1 space-y-1">
          {[
            { icon: LayoutDashboard, label: 'Dashboard', active: true },
            { icon: Package, label: 'Inventory' },
            { icon: Thermometer, label: 'Temperature' },
            { icon: Clock, label: 'Logs' },
            { icon: Settings, label: 'Settings' }
          ].map((item) => (
            <button
              key={item.label}
              className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all duration-200 group ${item.active
                  ? 'bg-blue-600/10 text-blue-400 border border-blue-500/20 shadow-inner'
                  : 'text-slate-400 hover:text-white hover:bg-white/5'
                }`}
            >
              <item.icon className={`w-5 h-5 ${item.active ? 'text-blue-400' : 'group-hover:scale-110 transition-transform'}`} />
              <span className="font-medium text-sm">{item.label}</span>
            </button>
          ))}
        </nav>

        <div className="mt-auto pt-6 border-t border-white/5">
          <div className="glass-card rounded-2xl p-4 bg-gradient-to-br from-blue-600/20 to-purple-600/10 border border-white/10">
            <TrendingUp className="w-6 h-6 text-blue-400 mb-2" />
            <p className="text-xs text-slate-400 mb-1">System Health</p>
            <p className="text-sm font-semibold text-white">All Systems Normal</p>
          </div>
        </div>
      </aside>

      {/* Main Content */}
      <main className="flex-1 flex flex-col min-w-0 overflow-hidden">

        {/* Header */}
        <header className="h-20 flex items-center justify-between px-8 border-b border-white/5 bg-white/2">
          <div className="flex items-center gap-4 flex-1">
            <div className="relative max-w-md w-full">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-slate-500" />
              <input
                type="text"
                placeholder="Search batches, units..."
                className="w-full bg-white/5 border border-white/10 rounded-full py-2.5 pl-10 pr-4 text-sm focus:bg-white/10 focus:ring-2 focus:ring-blue-500/30 outline-none transition-all placeholder:text-slate-600"
              />
            </div>
          </div>

          <div className="flex items-center gap-5">
            <button className="relative p-2 text-slate-400 hover:text-white transition-colors">
              <Bell className="w-5 h-5" />
              <span className="absolute top-2 right-2 w-2 h-2 bg-rose-500 rounded-full border-2 border-[#1e293b]"></span>
            </button>
            <div className="h-8 w-px bg-white/10 mx-1"></div>
            <div className="flex items-center gap-3 pl-2 cursor-pointer group">
              <div className="text-right hidden sm:block">
                <p className="text-sm font-semibold text-white group-hover:text-blue-400 transition-colors">Admin User</p>
                <p className="text-xs text-slate-500">Logistics Manager</p>
              </div>
              <div className="w-10 h-10 rounded-full border-2 border-blue-500/20 p-0.5 group-hover:border-blue-500/50 transition-all">
                <div className="w-full h-full rounded-full bg-gradient-to-tr from-blue-600 to-purple-600 flex items-center justify-center text-white font-bold text-xs uppercase">
                  AD
                </div>
              </div>
            </div>
          </div>
        </header>

        {/* Dashboard Area */}
        <div className="flex-1 overflow-y-auto p-8 custom-scrollbar">

          <div className="flex justify-between items-end mb-8">
            <div>
              <h2 className="text-3xl font-bold text-white mb-2">Logistics Dashboard</h2>
              <p className="text-slate-400">Monitoring real-time cold chain inventory and storage units.</p>
            </div>
            <div className="flex gap-3">
              <button
                onClick={handleFetchData}
                className="flex items-center gap-2 px-5 py-2.5 rounded-xl border border-white/10 text-slate-300 hover:bg-white/5 transition-colors text-sm font-medium"
              >
                <RefreshCw className={`w-4 h-4 ${loading ? 'animate-spin' : ''}`} />
                Refresh
              </button>
              <button
                onClick={() => setShowModal(true)}
                className="flex items-center gap-2 px-5 py-2.5 rounded-xl bg-blue-600 hover:bg-blue-500 text-white transition-all shadow-lg shadow-blue-600/20 text-sm font-semibold"
              >
                <PlusCircle className="w-4 h-4" />
                Register Batch
              </button>
            </div>
          </div>

          {/* Stats Grid */}
          <div className="grid grid-cols-1 md:grid-cols-2 xl:grid-cols-4 gap-6 mb-8">
            {[
              { label: 'Total Batches', value: data.length, icon: Package, color: 'from-blue-600 to-blue-400' },
              { label: 'Active Alerts', value: '2', icon: AlertTriangle, color: 'from-amber-500 to-orange-400', secondary: 'Critical' },
              { label: 'Storage Usage', value: '78%', icon: LayoutDashboard, color: 'from-purple-600 to-indigo-400' },
              { label: 'Avg Temp', value: '4.2°C', icon: Thermometer, color: 'from-emerald-500 to-teal-400' }
            ].map((stat) => (
              <div key={stat.label} className="glass-card rounded-2xl p-6 border border-white/10 flex flex-col justify-between hover:border-white/20 transition-all group">
                <div className="flex justify-between items-start mb-4">
                  <div className={`p-3 rounded-xl bg-gradient-to-br ${stat.color} shadow-lg shadow-blue-500/10 group-hover:scale-110 transition-transform duration-300`}>
                    <stat.icon className="w-6 h-6 text-white" />
                  </div>
                  {stat.secondary && (
                    <span className="px-2.5 py-1 rounded-full bg-rose-500/10 text-rose-500 text-[10px] font-bold uppercase tracking-wider border border-rose-500/20">
                      {stat.secondary}
                    </span>
                  )}
                </div>
                <div>
                  <p className="text-slate-400 text-sm mb-1">{stat.label}</p>
                  <p className="text-2xl font-bold text-white">{stat.value}</p>
                </div>
              </div>
            ))}
          </div>

          <div className="grid grid-cols-1 xl:grid-cols-3 gap-8">

            {/* Table Section */}
            <div className="xl:col-span-2 glass-card rounded-3xl border border-white/10 overflow-hidden flex flex-col">
              <div className="px-8 py-6 border-b border-white/5 flex justify-between items-center">
                <h3 className="font-bold text-lg text-white">Recent Vaccine Batches</h3>
                <ChevronRight className="w-5 h-5 text-slate-500 cursor-pointer hover:text-white transition-colors" />
              </div>
              <div className="flex-1 overflow-x-auto">
                {loading && data.length === 0 ? (
                  <div className="flex flex-col items-center justify-center p-20 gap-4">
                    <RefreshCw className="w-10 h-10 text-blue-500 animate-spin" />
                    <p className="text-slate-500">Synchronizing with mainframe...</p>
                  </div>
                ) : (
                  <table className="w-full text-left">
                    <thead>
                      <tr className="text-slate-500 text-xs font-bold uppercase tracking-widest bg-white/2">
                        <th className="px-8 py-4">Batch UID</th>
                        <th className="px-6 py-4">Product Type</th>
                        <th className="px-6 py-4">Expiry Date</th>
                        <th className="px-6 py-4">Assignment</th>
                        <th className="px-8 py-4 text-center">Actions</th>
                      </tr>
                    </thead>
                    <tbody className="divide-y divide-white/5">
                      {data.length === 0 ? (
                        <tr>
                          <td colSpan="5" className="px-8 py-20 text-center text-slate-500">No records found in current sector.</td>
                        </tr>
                      ) : (
                        data.map((item, idx) => (
                          <tr key={idx} className="hover:bg-white/5 transition-all duration-200 group">
                            <td className="px-8 py-5">
                              <span className="font-semibold text-white group-hover:text-blue-400 transition-colors uppercase tracking-tight">#{item.batchNumber}</span>
                            </td>
                            <td className="px-6 py-5">
                              <span className="px-3 py-1 rounded-lg bg-blue-500/10 text-blue-400 text-xs font-bold border border-blue-500/20">
                                {item.vaccineType}
                              </span>
                            </td>
                            <td className="px-6 py-5 text-sm text-slate-400 font-medium">
                              {item.expiryDate}
                            </td>
                            <td className="px-6 py-5">
                              <div className="flex items-center gap-2">
                                <div className={`w-2 h-2 rounded-full ${item.storageUnit ? 'bg-emerald-500 shadow-[0_0_8px_rgba(16,185,129,0.5)]' : 'bg-rose-500 shadow-[0_0_8px_rgba(244,63,94,0.5)]'}`}></div>
                                <span className="text-xs font-medium text-slate-300">
                                  {item.storageUnit ? `Unit ${item.storageUnit.unitId}` : 'Unassigned'}
                                </span>
                              </div>
                            </td>
                            <td className="px-8 py-5 text-center">
                              <div className="flex items-center justify-center gap-2 opacity-0 group-hover:opacity-100 transition-opacity translate-x-2 group-hover:translate-x-0 transition-transform">
                                <button className="p-2 rounded-lg hover:bg-blue-600/20 text-blue-400 transition-colors tooltip" title="Edit Record">
                                  <Settings className="w-4 h-4" />
                                </button>
                                <button className="p-2 rounded-lg hover:bg-rose-600/20 text-rose-400 transition-colors tooltip" title="Purge Record">
                                  <AlertTriangle className="w-4 h-4" />
                                </button>
                              </div>
                            </td>
                          </tr>
                        ))
                      )}
                    </tbody>
                  </table>
                )}
              </div>
            </div>

            {/* Distribution Chart Section */}
            <div className="glass-card rounded-3xl border border-white/10 p-8 flex flex-col">
              <h3 className="font-bold text-lg text-white mb-8">Stock Distribution</h3>
              <div className="flex-1 flex flex-col justify-center items-center">
                <div className="w-full relative py-4">
                  <Pie
                    data={getChartData()}
                    options={{
                      maintainAspectRatio: false,
                      plugins: {
                        legend: {
                          position: 'bottom',
                          labels: {
                            color: '#94a3b8',
                            font: { family: 'Outfit', size: 12, weight: '500' },
                            padding: 20,
                            usePointStyle: true,
                            pointStyle: 'circle'
                          }
                        },
                        tooltip: {
                          backgroundColor: '#1e293b',
                          titleFont: { family: 'Outfit', weight: 'bold' },
                          bodyFont: { family: 'Outfit' },
                          padding: 12,
                          cornerRadius: 12,
                          borderColor: 'rgba(255,255,255,0.1)',
                          borderWidth: 1
                        }
                      }
                    }}
                  />
                </div>
                {data.length > 0 && (
                  <div className="w-full mt-6 pt-6 border-t border-white/5 space-y-3">
                    <div className="flex justify-between items-center text-sm">
                      <span className="text-slate-400">Inventory Utilization</span>
                      <span className="text-white font-bold">64%</span>
                    </div>
                    <div className="w-full h-1.5 bg-white/5 rounded-full overflow-hidden">
                      <div className="h-full bg-gradient-to-r from-blue-600 to-purple-500 w-[64%] animate-in slide-in-from-left duration-1000"></div>
                    </div>
                  </div>
                )}
              </div>
            </div>

          </div>
        </div>
      </main>

      {/* Registration Modal */}
      {showModal && (
        <div className="fixed inset-0 flex items-center justify-center z-[100] p-6">
          <div className="absolute inset-0 bg-slate-950/60 backdrop-blur-sm animate-in fade-in duration-300" onClick={() => setShowModal(false)}></div>
          <div className="glass-card w-full max-w-lg rounded-[2.5rem] border border-white/10 p-10 relative z-10 shadow-2xl animate-in zoom-in duration-300 shadow-blue-500/5">
            <div className="flex justify-between items-start mb-8">
              <div>
                <h3 className="text-2xl font-bold text-white mb-2">Register Inventory</h3>
                <p className="text-slate-400">Enter new vaccine batch parameters.</p>
              </div>
              <button
                onClick={() => setShowModal(false)}
                className="p-2 rounded-2xl hover:bg-white/5 text-slate-500 hover:text-white transition-colors"
              >
                <X className="w-6 h-6" />
              </button>
            </div>

            <form onSubmit={handleSaveBatch} className="space-y-6">
              <div className="grid grid-cols-1 gap-6">
                <div className="space-y-2">
                  <label className="text-sm font-bold text-slate-300 ml-1">Batch Identifier</label>
                  <div className="relative group">
                    <Package className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 group-focus-within:text-blue-500 transition-colors" />
                    <input
                      required
                      type="text"
                      placeholder="e.g. BTC-9920-X"
                      className="w-full bg-white/5 border border-white/10 rounded-2xl py-3.5 pl-12 pr-4 text-white focus:bg-white/10 focus:border-blue-500/50 outline-none transition-all placeholder:text-slate-700 font-medium"
                      value={newBatch.batchNumber}
                      onChange={(e) => setNewBatch({ ...newBatch, batchNumber: e.target.value })}
                    />
                  </div>
                </div>

                <div className="space-y-2">
                  <label className="text-sm font-bold text-slate-300 ml-1">Vaccine Classification</label>
                  <div className="relative group">
                    <ShieldCheck className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 group-focus-within:text-purple-500 transition-colors" />
                    <input
                      required
                      type="text"
                      placeholder="e.g. Oxford-AstraZeneca"
                      className="w-full bg-white/5 border border-white/10 rounded-2xl py-3.5 pl-12 pr-4 text-white focus:bg-white/10 focus:border-purple-500/50 outline-none transition-all placeholder:text-slate-700 font-medium"
                      value={newBatch.vaccineType}
                      onChange={(e) => setNewBatch({ ...newBatch, vaccineType: e.target.value })}
                    />
                  </div>
                </div>

                <div className="space-y-2">
                  <label className="text-sm font-bold text-slate-300 ml-1">Expiration Timeline</label>
                  <div className="relative group">
                    <Clock className="absolute left-4 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 group-focus-within:text-emerald-500 transition-colors" />
                    <input
                      required
                      type="date"
                      className="w-full bg-white/5 border border-white/10 rounded-2xl py-3.5 pl-12 pr-4 text-white focus:bg-white/10 focus:border-emerald-500/50 outline-none transition-all placeholder:text-slate-700 font-medium [color-scheme:dark]"
                      value={newBatch.expiryDate}
                      onChange={(e) => setNewBatch({ ...newBatch, expiryDate: e.target.value })}
                    />
                  </div>
                </div>
              </div>

              <div className="flex gap-4 pt-4">
                <button
                  type="button"
                  onClick={() => setShowModal(false)}
                  className="flex-1 px-6 py-4 rounded-2xl border border-white/10 text-slate-300 font-bold hover:bg-white/5 transition-colors"
                >
                  Cancel
                </button>
                <button
                  type="submit"
                  className="flex-[2] px-6 py-4 bg-gradient-to-r from-blue-600 to-purple-600 text-white rounded-2xl font-bold hover:opacity-90 transition-all shadow-xl shadow-blue-600/20 active:scale-[0.98]"
                >
                  Validate & Save Record
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

    </div>
  );
}

export default App;