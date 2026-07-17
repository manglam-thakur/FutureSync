import { motion } from "framer-motion";

export default function StatCard({ title, value }) {

  return (

    <motion.div

      whileHover={{
        scale: 1.04,
        y: -5
      }}

      transition={{
        type: "spring",
        stiffness: 300
      }}

      className="relative overflow-hidden
      bg-white/5 backdrop-blur-lg
      border border-white/10
      rounded-3xl
      p-6
      shadow-2xl"

    >

      <div className="absolute inset-0 bg-gradient-to-br from-cyan-500/10 to-purple-500/10" />

      <div className="relative z-10">

        <h2 className="text-zinc-400 text-sm mb-3 uppercase tracking-wider">
          {title}
        </h2>

        <p className="text-5xl font-bold text-white">
          {value}
        </p>

      </div>

    </motion.div>
  );
}