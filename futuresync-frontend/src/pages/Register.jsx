import { useState } from "react";

import { useNavigate, Link } from "react-router-dom";

import API from "../services/api";

export default function Register() {

  const navigate = useNavigate();

  const [name, setName] = useState("");

  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");

  const [error, setError] = useState("");

  const handleRegister = async (e) => {

    e.preventDefault();

    try {

      await API.post(
        "/auth/register",
        {
          name,
          email,
          password
        }
      );

      navigate("/login");

    }

    catch (err) {

      setError(
        "Registration failed"
      );

    }
  };

  return (

    <div
      className="min-h-screen
      bg-black
      flex items-center justify-center
      px-6"
    >

      <div
        className="w-full max-w-md
        bg-zinc-900
        border border-zinc-800
        rounded-3xl
        p-10"
      >

        <h1
          className="text-4xl font-bold
          text-center mb-2"
        >

          Create Account

        </h1>

        <p
          className="text-zinc-400
          text-center mb-8"
        >

          Register to continue

        </p>

        <form
          onSubmit={handleRegister}
          className="space-y-5"
        >

          <input
            type="text"
            placeholder="Name"

            value={name}

            onChange={(e) =>
              setName(e.target.value)
            }

            className="w-full
            bg-zinc-800
            border border-zinc-700
            rounded-xl
            px-4 py-3
            text-white
            outline-none"
          />

          <input
            type="email"
            placeholder="Email"

            value={email}

            onChange={(e) =>
              setEmail(e.target.value)
            }

            className="w-full
            bg-zinc-800
            border border-zinc-700
            rounded-xl
            px-4 py-3
            text-white
            outline-none"
          />

          <input
            type="password"
            placeholder="Password"

            value={password}

            onChange={(e) =>
              setPassword(e.target.value)
            }

            className="w-full
            bg-zinc-800
            border border-zinc-700
            rounded-xl
            px-4 py-3
            text-white
            outline-none"
          />

          {
            error && (

              <p className="text-red-500 text-sm">

                {error}

              </p>
            )
          }

          <button
            type="submit"

            className="w-full
            bg-cyan-500
            hover:bg-cyan-400
            transition
            py-3 rounded-xl
            font-bold"
          >

            Register

          </button>

        </form>

        <p
          className="text-zinc-400
          text-center mt-6"
        >

          Already have an account?

          <Link
            to="/login"
            className="text-cyan-400 ml-2"
          >

            Login

          </Link>

        </p>

      </div>

    </div>
  );
}