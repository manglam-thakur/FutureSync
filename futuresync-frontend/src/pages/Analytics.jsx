import { useEffect, useState } from "react";

import { motion } from "framer-motion";

import Layout from "../components/Layout";
import AnalyticsChart from "../components/AnalyticsChart";

import API from "../services/api";

export default function Analytics() {

  const [data, setData] = useState([]);

  const userId =
    localStorage.getItem("userId");

  useEffect(() => {

    if (userId) {

      fetchAnalytics();

    }

  }, []);

  const fetchAnalytics = async () => {

    try {

      const response =

        await API.get(
          `/visualization/${userId}`
        );

      setData(
        response.data
      );

    }

    catch (error) {

      console.log(error);

    }
  };

  const totalRecords =
    data.length;

  const bestProductivity =

    data.length > 0

      ? Math.max(
          ...data.map(
            item =>
              item.productivityScore
          )
        )

      : 0;

  const averageStudyHours =

    data.length > 0

      ? (
          data.reduce(

            (sum, item) =>

              sum + item.studyHours,

            0

          ) / data.length

        ).toFixed(1)

      : 0;

  const totalTasks =

    data.reduce(

      (sum, item) =>

        sum + item.tasksCompleted,

      0

    );

  return (

    <Layout>

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
      >

        <div className="mb-12">

          <h1
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

            Analytics Dashboard

          </h1>

          <p
            className="text-zinc-400
            text-lg
            max-w-3xl
            leading-8"
          >

            Analyze productivity patterns,
            study consistency, and
            performance trends through
            intelligent analytics.

          </p>

        </div>

        <div
          className="grid
          grid-cols-1
          md:grid-cols-2
          xl:grid-cols-4
          gap-6"
        >

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-white/5
            backdrop-blur-xl
            border border-white/10
            rounded-3xl
            p-6
            shadow-2xl"
          >

            <h2
              className="text-zinc-400
              mb-3
              text-sm
              uppercase
              tracking-wider"
            >

              Total Records

            </h2>

            <p
              className="text-5xl
              font-bold
              text-white"
            >

              {totalRecords}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-cyan-500/20
            to-blue-600/20
            backdrop-blur-xl
            border border-cyan-500/20
            rounded-3xl
            p-6
            shadow-2xl"
          >

            <h2
              className="text-zinc-300
              mb-3
              text-sm
              uppercase
              tracking-wider"
            >

              Best Productivity

            </h2>

            <p
              className="text-5xl
              font-bold
              text-cyan-400"
            >

              {bestProductivity}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-green-500/20
            to-emerald-600/20
            backdrop-blur-xl
            border border-green-500/20
            rounded-3xl
            p-6
            shadow-2xl"
          >

            <h2
              className="text-zinc-300
              mb-3
              text-sm
              uppercase
              tracking-wider"
            >

              Avg Study Hours

            </h2>

            <p
              className="text-5xl
              font-bold
              text-green-400"
            >

              {averageStudyHours}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-purple-500/20
            to-pink-600/20
            backdrop-blur-xl
            border border-purple-500/20
            rounded-3xl
            p-6
            shadow-2xl"
          >

            <h2
              className="text-zinc-300
              mb-3
              text-sm
              uppercase
              tracking-wider"
            >

              Tasks Completed

            </h2>

            <p
              className="text-5xl
              font-bold
              text-purple-400"
            >

              {totalTasks}

            </p>

          </motion.div>

        </div>

        <div className="mt-10">

          <AnalyticsChart
            data={data}
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

          className="mt-10
          grid
          grid-cols-1
          xl:grid-cols-2
          gap-6"
        >

          <div
            className="bg-white/5
            backdrop-blur-xl
            border border-white/10
            rounded-3xl
            p-8"
          >

            <h2
              className="text-3xl
              font-bold
              mb-5"
            >

              Performance Insight

            </h2>

            <p
              className="text-zinc-400
              leading-8
              text-lg"
            >

              Your productivity metrics
              indicate improving consistency
              and stronger study discipline
              over recent sessions.

            </p>

          </div>

          <div
            className="bg-white/5
            backdrop-blur-xl
            border border-white/10
            rounded-3xl
            p-8"
          >

            <h2
              className="text-3xl
              font-bold
              mb-5"
            >

              Growth Recommendation

            </h2>

            <p
              className="text-zinc-400
              leading-8
              text-lg"
            >

              Focus on maintaining longer
              productive streaks and reducing
              distractions to achieve higher
              future productivity growth.

            </p>

          </div>

        </motion.div>

      </motion.div>

    </Layout>
  );
}