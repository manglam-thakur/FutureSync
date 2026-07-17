import { useState } from "react";

import { motion } from "framer-motion";

import Layout from "../components/Layout";

import API from "../services/api";

import { useNavigate } from "react-router-dom";

export default function AddActivity() {

  const navigate =
    useNavigate();

  const [formData, setFormData] =
    useState({

      studyHours: "",

      tasksCompleted: "",

      distractionTime: ""

    });

  const handleChange = (e) => {

    setFormData({

      ...formData,

      [e.target.name]:
        e.target.value

    });
  };

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      const userId =
        localStorage.getItem("userId");

      const payload = {

        date:
          new Date()
            .toISOString()
            .split("T")[0],

        studyHours:
          Number(formData.studyHours),

        tasksCompleted:
          Number(formData.tasksCompleted),

        distractionTime:
          Number(formData.distractionTime),

        user: {
          id: Number(userId)
        }
      };

      await API.post(
        "/activity/add",
        payload
      );

      alert(
        "Activity Added Successfully 🚀"
      );

      navigate("/dashboard");

    }

    catch (error) {

      console.log(error);

      alert(
        "Failed to add activity"
      );
    }
  };

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
          duration: 0.5
        }}

        className="max-w-3xl mx-auto"
      >

        <div className="mb-10">

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

            Add Daily Activity

          </h1>

          <p
            className="text-zinc-400
            text-lg
            leading-8"
          >

            Track your productivity,
            study discipline,
            and performance metrics.

          </p>

        </div>

        <form

          onSubmit={handleSubmit}

          className="bg-white/5
          backdrop-blur-xl
          border border-white/10
          rounded-3xl
          p-8
          space-y-6"
        >

          <div>

            <label
              className="block
              mb-2
              text-zinc-300"
            >

              Study Hours

            </label>

            <input

              type="number"

              name="studyHours"

              value={formData.studyHours}

              onChange={handleChange}

              required

              className="w-full
              bg-zinc-900
              border border-zinc-700
              rounded-2xl
              px-5
              py-4
              text-white
              outline-none
              focus:border-cyan-500"
            />

          </div>

          <div>

            <label
              className="block
              mb-2
              text-zinc-300"
            >

              Tasks Completed

            </label>

            <input

              type="number"

              name="tasksCompleted"

              value={formData.tasksCompleted}

              onChange={handleChange}

              required

              className="w-full
              bg-zinc-900
              border border-zinc-700
              rounded-2xl
              px-5
              py-4
              text-white
              outline-none
              focus:border-cyan-500"
            />

          </div>

          <div>

            <label
              className="block
              mb-2
              text-zinc-300"
            >

              Distraction Time

            </label>

            <input

              type="number"

              name="distractionTime"

              value={formData.distractionTime}

              onChange={handleChange}

              required

              className="w-full
              bg-zinc-900
              border border-zinc-700
              rounded-2xl
              px-5
              py-4
              text-white
              outline-none
              focus:border-cyan-500"
            />

          </div>

          <button

            type="submit"

            className="w-full
            py-4
            rounded-2xl
            bg-gradient-to-r
            from-cyan-500
            to-blue-600
            text-white
            font-bold
            text-lg
            hover:scale-[1.02]
            transition-all
            duration-300"
          >

            Save Activity

          </button>

        </form>

      </motion.div>

    </Layout>
  );
}