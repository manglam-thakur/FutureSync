import { LogOut } from "lucide-react";

import { useNavigate } from "react-router-dom";

export default function Navbar() {

  const navigate =
    useNavigate();

  const name =
    localStorage.getItem("name");

  const handleLogout = () => {

    localStorage.clear();

    navigate("/login");

  };

  return (

    <div
      className="flex
      justify-between
      items-center
      px-8
      py-6
      border-b
      border-white/10
      backdrop-blur-xl"
    >

      <div>

        <h1
          className="text-3xl
          font-bold
          text-white"
        >

          Dashboard

        </h1>

        <p
          className="text-zinc-400
          mt-1"
        >

          Welcome back, {name} 👋

        </p>

      </div>

      <div
        className="flex
        items-center
        gap-4"
      >

        <div
          className="bg-cyan-500/10
          border border-cyan-500/20
          text-cyan-400
          px-5
          py-2
          rounded-2xl
          font-semibold"
        >

          Productivity Mode

        </div>

        <button

          onClick={handleLogout}

          className="flex
          items-center
          gap-2
          bg-red-500/10
          hover:bg-red-500/20
          border border-red-500/20
          text-red-400
          px-5
          py-2
          rounded-2xl
          transition-all
          duration-300"
        >

          <LogOut size={18} />

          Logout

        </button>

      </div>

    </div>
  );
}