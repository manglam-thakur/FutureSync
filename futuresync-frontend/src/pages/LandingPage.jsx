import { motion } from "framer-motion";

import {
  BarChart3,
  BrainCircuit,
  Lightbulb
} from "lucide-react";

import { Link } from "react-router-dom";

export default function LandingPage() {

  const features = [

    {
      icon: <BarChart3 size={24} />,
      title: "Analytics",
      description:
        "Track productivity trends and performance insights."
    },

    {
      icon: <BrainCircuit size={24} />,
      title: "Prediction",
      description:
        "Analyze future productivity and consistency."
    },

    {
      icon: <Lightbulb size={24} />,
      title: "Recommendations",
      description:
        "Get smart suggestions to improve focus and growth."
    }
  ];

  return (

    <div className="min-h-screen bg-black text-white">

      <nav
        className="flex items-center justify-between
        px-10 py-6 border-b border-zinc-800"
      >

        <h1
          className="text-3xl font-bold
          bg-gradient-to-r from-cyan-400 to-blue-500
          bg-clip-text text-transparent"
        >

          FutureSync

        </h1>

        <Link to="/dashboard">

          <button
            className="bg-cyan-500 hover:bg-cyan-400
            transition px-5 py-2 rounded-xl font-semibold"
          >

            Dashboard

          </button>

        </Link>

      </nav>

      <section
        className="max-w-6xl mx-auto
        px-10 py-28 text-center"
      >

        <motion.h1

          initial={{
            opacity: 0,
            y: 20
          }}

          animate={{
            opacity: 1,
            y: 0
          }}

          transition={{
            duration: 0.6
          }}

          className="text-6xl font-extrabold
          leading-tight mb-8"
        >

          Smart Productivity
          <span
            className="bg-gradient-to-r
            from-cyan-400 to-blue-500
            bg-clip-text text-transparent"
          >

            {" "}Insights

          </span>

        </motion.h1>

        <motion.p

          initial={{
            opacity: 0
          }}

          animate={{
            opacity: 1
          }}

          transition={{
            delay: 0.2
          }}

          className="text-zinc-400 text-xl
          leading-9 max-w-3xl mx-auto mb-12"
        >

          Monitor productivity, track discipline,
          analyze performance trends, and improve
          consistency through a modern analytics platform.

        </motion.p>

        <Link to="/dashboard">

          <motion.button

            whileHover={{
              scale: 1.05
            }}

            whileTap={{
              scale: 0.95
            }}

            className="bg-gradient-to-r
            from-cyan-500 to-blue-600
            px-8 py-4 rounded-2xl
            text-lg font-bold"
          >

            Open Dashboard

          </motion.button>

        </Link>

      </section>

      <section
        className="max-w-6xl mx-auto
        px-10 pb-24"
      >

        <div
          className="grid grid-cols-1
          md:grid-cols-3 gap-6"
        >

          {
            features.map((feature) => (

              <motion.div

                key={feature.title}

                whileHover={{
                  y: -5
                }}

                className="bg-zinc-900
                border border-zinc-800
                rounded-3xl p-8"
              >

                <div
                  className="w-12 h-12 rounded-xl
                  bg-cyan-500/10
                  flex items-center justify-center
                  text-cyan-400 mb-5"
                >

                  {feature.icon}

                </div>

                <h2 className="text-2xl font-bold mb-4">

                  {feature.title}

                </h2>

                <p className="text-zinc-400 leading-8">

                  {feature.description}

                </p>

              </motion.div>
            ))
          }

        </div>

      </section>

    </div>
  );
}