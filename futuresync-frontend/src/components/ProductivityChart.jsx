import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer,
  Area,
  AreaChart
} from "recharts";

import { motion } from "framer-motion";

export default function ProductivityChart({ data = [] }) {

  return (

    <motion.div

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

      className="relative overflow-hidden
      bg-white/5 backdrop-blur-xl
      border border-white/10
      rounded-3xl
      p-8
      mt-10
      shadow-2xl"
    >

      <div className="absolute inset-0 bg-gradient-to-br from-cyan-500/5 to-purple-500/5" />

      <div className="relative z-10">

        <div className="mb-8">

          <h2 className="text-3xl font-bold text-white mb-2">

            Productivity Trend

          </h2>

          <p className="text-zinc-400">

            Monitor productivity growth and performance patterns over time.

          </p>

        </div>

        <div className="w-full h-[350px] min-w-0">

          <ResponsiveContainer width="100%" height="100%">

            <AreaChart data={data}>

              <defs>

                <linearGradient
                  id="colorProductivity"
                  x1="0"
                  y1="0"
                  x2="0"
                  y2="1"
                >

                  <stop
                    offset="5%"
                    stopColor="#06b6d4"
                    stopOpacity={0.8}
                  />

                  <stop
                    offset="95%"
                    stopColor="#06b6d4"
                    stopOpacity={0}
                  />

                </linearGradient>

              </defs>

              <CartesianGrid
                strokeDasharray="3 3"
                stroke="#27272a"
              />

              <XAxis
                dataKey="date"
                stroke="#a1a1aa"
              />

              <YAxis
                stroke="#a1a1aa"
              />

              <Tooltip

                contentStyle={{
                  backgroundColor: "#09090b",
                  border: "1px solid #27272a",
                  borderRadius: "16px",
                  color: "#fff"
                }}

                labelStyle={{
                  color: "#06b6d4"
                }}
              />

              <Area
                type="monotone"
                dataKey="productivityScore"
                stroke="#06b6d4"
                fillOpacity={1}
                fill="url(#colorProductivity)"
              />

              <Line
                type="monotone"
                dataKey="productivityScore"
                stroke="#06b6d4"
                strokeWidth={4}
                dot={{
                  r: 5,
                  fill: "#06b6d4"
                }}
                activeDot={{
                  r: 8
                }}
              />

            </AreaChart>

          </ResponsiveContainer>

        </div>

      </div>

    </motion.div>
  );
}