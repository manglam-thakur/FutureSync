import Sidebar from "./Sidebar";
import Navbar from "./Navbar";

export default function Layout({ children }) {

  return (

    <div className="flex bg-black min-h-screen text-white">

      <Sidebar />

      <div className=
"flex-1">
        <Navbar />

        <div className="p-8">

          {children}

        </div>

      </div>

    </div>
  );
}