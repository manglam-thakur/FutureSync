import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  Tooltip,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";

export default function AnalyticsChart({ data = [] }) {

  return (

    <div className="bg-zinc-900 p-6 rounded-2xl border border-zinc-800 mt-10">

      <h2 className="text-2xl font-bold mb-6">
        Study Analytics
      </h2>

      <div className="w-full h-[350px]">

        <ResponsiveContainer width="100%" height="100%">

          <BarChart data={data}>

            <CartesianGrid strokeDasharray="3 3" />

            <XAxis dataKey="date" />

            <YAxis />

            <Tooltip />

            <Bar
              dataKey="studyHours"
              fill="#06b6d4"
              radius={[10, 10, 0, 0]}
            />

          </BarChart>

        </ResponsiveContainer>

      </div>

    </div>
  );
}