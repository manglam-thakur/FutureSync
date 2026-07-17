import { useEffect, useState } from "react";

import { motion } from "framer-motion";

import ProductivityChart from "../components/ProductivityChart";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import StatCard from "../components/StatCard";

import API from "../services/api";

export default function Dashboard() {

  const [dashboard, setDashboard] =
    useState(null);

  const [chartData, setChartData] =
    useState([]);

  const userId =
    localStorage.getItem("userId");

  useEffect(() => {

    if (userId) {

      fetchDashboard();

    }

  }, []);

  const fetchDashboard = async () => {

    try {

      const response =

        await API.get(
          `/dashboard/${userId}`
        );

      setDashboard(
        response.data
      );

      const chartResponse =

        await API.get(
          `/visualization/${userId}`
        );

      setChartData(
        chartResponse.data
      );

    }

    catch (error) {

      console.log(error);

    }
  };

  if (!dashboard) {

    return (

      <div
        className="min-h-screen
        bg-black
        text-white
        flex items-center justify-center"
      >

        <div
          className="text-3xl
          font-bold animate-pulse"
        >

          Loading Dashboard...

        </div>

      </div>
    );
  }

  return (

    <div
      className="flex
      bg-black
      min-h-screen
      text-white
      overflow-hidden"
    >

      <Sidebar />

      <div
        className="flex-1
        bg-gradient-to-br
        from-black
        via-zinc-950
        to-black"
      >

        <Navbar />

        <motion.div

          initial={{
            opacity: 0,
            y: 30
          }}

          animate={{
            opacity: 1,
            y: 0
          }}

          transition={{
            duration: 0.7
          }}

          className="p-8"
        >

          <div className="mb-12">

            <motion.h1

              initial={{
                opacity: 0,
                x: -20
              }}

              animate={{
                opacity: 1,
                x: 0
              }}

              transition={{
                delay: 0.2
              }}

              className="text-5xl
              font-extrabold
              mb-4
              bg-gradient-to-r
              from-cyan-400
              via-blue-500
              to-purple-500
              bg-clip-text
              text-transparent"
            >

              Productivity Dashboard

            </motion.h1>

            <motion.p

              initial={{
                opacity: 0
              }}

              animate={{
                opacity: 1
              }}

              transition={{
                delay: 0.4
              }}

              className="text-zinc-400
              text-lg
              max-w-2xl
              leading-8"
            >

              Track your productivity,
              discipline, and future growth
              through intelligent insights
              and performance analytics.

            </motion.p>

          </div>

          <div
            className="grid
            grid-cols-1
            md:grid-cols-2
            xl:grid-cols-4
            gap-6"
          >

            <StatCard
              title="Discipline Score"
              value={dashboard.disciplineScore}
            />

            <StatCard
              title="Current Streak"
              value={dashboard.currentStreak}
            />

            <StatCard
              title="Study Hours"
              value={dashboard.totalStudyHours}
            />

            <StatCard
              title="Future Status"
              value={dashboard.futureStatus}
            />

          </div>

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
              delay: 0.5
            }}

            className="mt-10"
          >

            <ProductivityChart
              data={chartData}
            />

          </motion.div>

        </motion.div>

      </div>

    </div>
  );
}