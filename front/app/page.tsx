'use client'

import { useState } from 'react'
import { Activity, Plus, Trash2 } from 'lucide-react'

interface Activity {
  id: string
  title: string
  description: string
  timestamp: string
}

export default function Home() {
  const [activities, setActivities] = useState<Activity[]>([])
  const [title, setTitle] = useState('')
  const [description, setDescription] = useState('')

  const addActivity = () => {
    if (!title.trim()) return

    const newActivity: Activity = {
      id: Date.now().toString(),
      title,
      description,
      timestamp: new Date().toLocaleString(),
    }

    setActivities([newActivity, ...activities])
    setTitle('')
    setDescription('')
  }

  const deleteActivity = (id: string) => {
    setActivities(activities.filter(activity => activity.id !== id))
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-900 via-slate-800 to-slate-900">
      <div className="max-w-4xl mx-auto px-4 py-8">
        {/* Header */}
        <div className="mb-8">
          <div className="flex items-center gap-3 mb-2">
            <Activity className="w-8 h-8 text-amber-500" />
            <h1 className="text-4xl font-bold text-white">Honeybadger Tracker</h1>
          </div>
          <p className="text-slate-400">Track and manage your activities</p>
        </div>

        {/* Input Section */}
        <div className="bg-slate-800 rounded-lg p-6 mb-8 border border-slate-700">
          <h2 className="text-xl font-semibold text-white mb-4">New Activity</h2>
          <div className="space-y-4">
            <input
              type="text"
              placeholder="Activity title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
              onKeyPress={(e) => e.key === 'Enter' && addActivity()}
              className="w-full px-4 py-2 bg-slate-700 text-white placeholder-slate-400 rounded-lg border border-slate-600 focus:border-amber-500 focus:outline-none transition"
            />
            <textarea
              placeholder="Description (optional)"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
              className="w-full px-4 py-2 bg-slate-700 text-white placeholder-slate-400 rounded-lg border border-slate-600 focus:border-amber-500 focus:outline-none transition resize-none"
              rows={3}
            />
            <button
              onClick={addActivity}
              className="w-full bg-amber-500 hover:bg-amber-600 text-white font-semibold py-2 px-4 rounded-lg flex items-center justify-center gap-2 transition"
            >
              <Plus className="w-5 h-5" />
              Add Activity
            </button>
          </div>
        </div>

        {/* Activities List */}
        <div className="space-y-4">
          {activities.length === 0 ? (
            <div className="text-center py-12 bg-slate-800 rounded-lg border border-slate-700">
              <Activity className="w-12 h-12 text-slate-600 mx-auto mb-3" />
              <p className="text-slate-400">No activities yet. Create one to get started!</p>
            </div>
          ) : (
            activities.map((activity) => (
              <div
                key={activity.id}
                className="bg-slate-800 rounded-lg p-6 border border-slate-700 hover:border-amber-500 transition"
              >
                <div className="flex justify-between items-start mb-2">
                  <h3 className="text-lg font-semibold text-white">{activity.title}</h3>
                  <button
                    onClick={() => deleteActivity(activity.id)}
                    className="text-slate-400 hover:text-red-500 transition"
                  >
                    <Trash2 className="w-5 h-5" />
                  </button>
                </div>
                {activity.description && (
                  <p className="text-slate-300 mb-3">{activity.description}</p>
                )}
                <p className="text-sm text-slate-500">{activity.timestamp}</p>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  )
}
