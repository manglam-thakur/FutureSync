import {
  LayoutDashboard,
  BarChart3,
  BrainCircuit,
  Lightbulb,
  Settings
} from "lucide-react";

import { Link, useLocation } from "react-router-dom";

import { motion } from "framer-motion";

export default function Sidebar() {

  const location = useLocation();

  const menuItems = [

    {
      name: "Dashboard",
      path: "/",
      icon: <LayoutDashboard size={20} />
    },

    {
      name: "Analytics",
      path: "/analytics",
      icon: <BarChart3 size={20} />
    },

    {
      name: "Prediction Engine",
      path: "/prediction",
      icon: <BrainCircuit size={20} />
    },

    {
      name: "Smart Recommendations",
      path: "/recommendations",
      icon: <Lightbulb size={20} />
    },

    {
      name: "Settings",
      path: "/settings",
      icon: <Settings size={20} />
    }
  ];

  return (

    <div
      className="w-72 min-h-screen
      bg-black/60 backdrop-blur-xl
      border-r border-white/10
      p-6 flex flex-col"
    >

      <motion.div

        initial={{
          opacity: 0,
          y: -20
        }}

        animate={{
          opacity: 1,
          y: 0
        }}

        transition={{
          duration: 0.5
        }}

        className="mb-14"
      >

        <h1 className="text-4xl font-extrabold bg-gradient-to-r from-cyan-400 to-blue-500 bg-clip-text text-transparent">

          FutureSync

        </h1>

        <p className="text-zinc-500 mt-2 text-sm">

          Productivity Intelligence Platform

        </p>

      </motion.div>

      <div className="space-y-3 flex-1">

        {
          menuItems.map((item, index) => (

            <Link
              key={item.name}
              to={item.path}
            >

              <motion.div

                initial={{
                  opacity: 0,
                  x: -20
                }}

                animate={{
                  opacity: 1,
                  x: 0
                }}

                transition={{
                  delay: index * 0.08
                }}

                whileHover={{
                  scale: 1.03,
                  x: 5
                }}

                className={`group relative flex items-center gap-4 p-4 rounded-2xl transition-all duration-300 overflow-hidden

                ${
                  location.pathname === item.path

                    ? "bg-gradient-to-r from-cyan-500 to-blue-600 text-white shadow-lg shadow-cyan-500/20"

                    : "text-zinc-400 hover:bg-zinc-900 hover:text-white"
                }`}
              >

                <div
                  className={`absolute inset-0 opacity-0 group-hover:opacity-100 transition duration-500

                  ${
                    location.pathname === item.path
                      ? ""
                      : "bg-gradient-to-r from-cyan-500/5 to-purple-500/5"
                  }`}
                />

                <div className="relative z-10">

                  {item.icon}

                </div>

                <span className="relative z-10 font-medium text-sm tracking-wide">

                  {item.name}

                </span>

              </motion.div>

            </Link>
          ))
        }

      </div>

      <div className="mt-10 border-t border-white/10 pt-6">

        <div className="bg-zinc-900/80 border border-zinc-800 rounded-2xl p-5">

          <p className="text-zinc-500 text-sm mb-2">
            Current Status
          </p>

          <h2 className="text-white text-lg font-bold">
            Good Progress 🚀
          </h2>

          <p className="text-zinc-400 text-sm mt-2 leading-6">

            Your productivity metrics are improving steadily.

          </p>

        </div>

      </div>

    </div>
  );
}