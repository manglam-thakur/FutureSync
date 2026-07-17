import { useState } from "react";

import { useNavigate, Link } from "react-router-dom";

import API from "../services/api";

export default function Login() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");

  const [error, setError] = useState("");

  const handleLogin = async (e) => {

    e.preventDefault();

    try {

      const response = await API.post(
        "/auth/login",
        {
          email,
          password
        }
      );

      localStorage.setItem(
        "token",
        response.data.token
      );

      localStorage.setItem(
        "userId",
        response.data.userId
      );

      localStorage.setItem(
        "name",
        response.data.name
      );

      localStorage.setItem(
        "email",
        response.data.email
      );

      navigate("/dashboard");

    }

    catch (err) {

      setError(
        "Invalid email or password"
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

          Welcome Back

        </h1>

        <p
          className="text-zinc-400
          text-center mb-8"
        >

          Login to continue

        </p>

        <form
          onSubmit={handleLogin}
          className="space-y-5"
        >

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

            Login

          </button>

        </form>

        <p
          className="text-zinc-400
          text-center mt-6"
        >

          Don’t have an account?

          <Link
            to="/register"
            className="text-cyan-400 ml-2"
          >

            Register

          </Link>

        </p>

      </div>

    </div>
  );
}