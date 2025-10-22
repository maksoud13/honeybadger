export default function HeyPage() {
  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900 flex items-center justify-center">
      <div className="text-center">
        <h1 className="text-6xl font-bold text-amber-500 mb-4">Hey! 👋</h1>
        <p className="text-2xl text-white mb-8">Welcome to Honeybadger Tracker</p>
        <a
          href="/"
          className="inline-block bg-amber-500 hover:bg-amber-600 text-white font-semibold py-3 px-8 rounded-lg transition"
        >
          Go to Tracker
        </a>
      </div>
    </div>
  )
}
