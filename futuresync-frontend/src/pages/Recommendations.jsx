import { useEffect, useState } from "react";

import { motion } from "framer-motion";

import Layout from "../components/Layout";

import API from "../services/api";

export default function Recommendations() {

  const [recommendation, setRecommendation] =
    useState(null);

  const userId =
    localStorage.getItem("userId");

  useEffect(() => {

    if (userId) {

      fetchRecommendation();

    }

  }, []);

  const fetchRecommendation = async () => {

    try {

      const response =

        await API.get(
          `/recommendation/${userId}`
        );

      setRecommendation(
        response.data
      );

    }

    catch (error) {

      console.log(error);

    }
  };

  if (!recommendation) {

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

            Loading Recommendations...

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

            Smart Recommendations

          </h1>

          <p
            className="text-zinc-400
            text-lg
            max-w-3xl
            leading-8"
          >

            Personalized productivity insights
            and growth recommendations
            designed to improve consistency,
            focus, and long-term performance.

          </p>

        </div>

        <div
          className="grid
          grid-cols-1
          md:grid-cols-3
          gap-6"
        >

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
              text-zinc-300
              mb-3"
            >

              Priority

            </h2>

            <p
              className="text-5xl
              font-bold
              text-red-400"
            >

              {recommendation.priority}

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.02,
              y: -5
            }}

            className="md:col-span-2
            bg-gradient-to-br
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
              text-zinc-300
              mb-3"
            >

              Recommendation

            </h2>

            <p
              className="text-2xl
              font-bold
              leading-10
              text-white"
            >

              {recommendation.recommendation}

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
            delay: 0.3
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

            Why This Matters

          </h2>

          <p
            className="text-zinc-300
            text-lg
            leading-9"
          >

            {recommendation.reason}

          </p>

        </motion.div>

        <div
          className="mt-10
          grid
          grid-cols-1
          md:grid-cols-2
          gap-6"
        >

          <motion.div

            whileHover={{
              scale: 1.02,
              y: -5
            }}

            className="bg-white/5
            backdrop-blur-xl
            border border-white/10
            rounded-3xl
            p-6"
          >

            <h2
              className="text-2xl
              font-bold
              mb-4"
            >

              Suggested Improvement

            </h2>

            <p
              className="text-zinc-300
              leading-8"
            >

              Build stronger routines,
              maintain consistency,
              and improve focus through
              disciplined daily habits
              and structured productive sessions.

            </p>

          </motion.div>

          <motion.div

            whileHover={{
              scale: 1.02,
              y: -5
            }}

            className="bg-white/5
            backdrop-blur-xl
            border border-white/10
            rounded-3xl
            p-6"
          >

            <h2
              className="text-2xl
              font-bold
              mb-4"
            >

              Productivity Insight

            </h2>

            <p
              className="text-zinc-300
              leading-8"
            >

              Small improvements repeated
              consistently over time
              create significant long-term
              productivity growth
              and stronger discipline patterns.

            </p>

          </motion.div>

        </div>

      </motion.div>

    </Layout>
  );
}