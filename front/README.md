# Honeybadger Tracker - Frontend

A modern Next.js frontend application for the Honeybadger Tracker system.

## Tech Stack

- **Next.js 14** - React framework with App Router
- **TypeScript** - Type-safe development
- **Tailwind CSS** - Utility-first styling
- **Lucide React** - Beautiful icons
- **Axios** - HTTP client for API calls

## Getting Started

### Prerequisites

- Node.js 18+ 
- npm or yarn

### Installation

1. Install dependencies:
```bash
npm install
```

2. Run the development server:
```bash
npm run dev
```

3. Open [http://localhost:3000](http://localhost:3000) in your browser

## Available Scripts

- `npm run dev` - Start development server
- `npm run build` - Build for production
- `npm start` - Start production server
- `npm run lint` - Run ESLint

## Project Structure

```
app/
├── layout.tsx      - Root layout
├── page.tsx        - Home page
└── globals.css     - Global styles

components/        - Reusable components (to be added)
public/           - Static assets
```

## Features

- Activity tracking interface
- Add, view, and delete activities
- Responsive design
- Dark theme UI

## Development

The project uses Next.js App Router with TypeScript. All components are built with React 18 and styled with Tailwind CSS.

## Deployment

Build for production:
```bash
npm run build
npm start
```

The application can be deployed to Vercel, Netlify, or any Node.js hosting platform.
