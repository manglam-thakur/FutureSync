import { useEffect, useState } from "react";

import { motion } from "framer-motion";

import Layout from "../components/Layout";

import API from "../services/api";

export default function Prediction() {

  const [prediction, setPrediction] =
    useState(null);

  const userId =
    localStorage.getItem("userId");

  useEffect(() => {

    if (userId) {

      fetchPrediction();

    }

  }, []);

  const fetchPrediction = async () => {

    try {

      const response =

        await API.get(
          `/prediction/metrics/${userId}`
        );

      setPrediction(
        response.data
      );

    }

    catch (error) {

      console.log(error);

    }
  };

  if (!prediction) {

    return (

      <Layout>

        <div
          className="min-h-[70vh]
          flex
          items-center
          justify-center"
        >

          <div
            className="text-3xl
            font-bold
            animate-pulse
            text-white"
          >

            Loading Prediction...

          </div>

        </div>

      </Layout>
    );
  }

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

            Prediction Insights

          </h1>

          <p
            className="text-zinc-400
            text-lg
            max-w-3xl
            leading-8"
          >

            Analyze future productivity,
            consistency trends, and
            performance growth through
            predictive intelligence.

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

            className="bg-gradient-to-br
            from-cyan-500/20
            to-blue-700/20
            border border-cyan-500/20
            backdrop-blur-xl
            p-6
            rounded-3xl
            shadow-2xl"
          >

            <h2
              className="text-lg
              mb-3
              text-zinc-300"
            >

              Future Productivity

            </h2>

            <p
              className="text-4xl
              font-bold
              text-cyan-400"
            >

              {prediction.futureProductivity}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-purple-500/20
            to-pink-700/20
            border border-purple-500/20
            backdrop-blur-xl
            p-6
            rounded-3xl
            shadow-2xl"
          >

            <h2
              className="text-lg
              mb-3
              text-zinc-300"
            >

              Discipline Trend

            </h2>

            <p
              className="text-4xl
              font-bold
              text-purple-400"
            >

              {prediction.disciplineTrend}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-red-500/20
            to-orange-600/20
            border border-red-500/20
            backdrop-blur-xl
            p-6
            rounded-3xl
            shadow-2xl"
          >

            <h2
              className="text-lg
              mb-3
              text-zinc-300"
            >

              Consistency Risk

            </h2>

            <p
              className="text-4xl
              font-bold
              text-red-400"
            >

              {prediction.consistencyRisk}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.03,
              y: -5
            }}

            className="bg-gradient-to-br
            from-green-500/20
            to-emerald-700/20
            border border-green-500/20
            backdrop-blur-xl
            p-6
            rounded-3xl
            shadow-2xl"
          >

            <h2
              className="text-lg
              mb-3
              text-zinc-300"
            >

              Predicted Score

            </h2>

            <p
              className="text-5xl
              font-bold
              text-green-400"
            >

              {prediction.predictedScore}

            </p>

          </motion.div>

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
            delay: 0.4
          }}

          className="mt-10
          bg-white/5
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

            Performance Analysis

          </h2>

          <p
            className="text-zinc-300
            text-lg
            leading-9"
          >

            Based on your recent productivity
            behavior, the system predicts
            future growth patterns,
            discipline consistency,
            and long-term performance trends.

            Maintaining productive habits,
            consistent routines,
            and focused work sessions
            can significantly improve
            future productivity outcomes.

          </p>

        </motion.div>

      </motion.div>

    </Layout>
  );
}