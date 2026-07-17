import {
  BrowserRouter,
  Routes,
  Route,
  Navigate
} from "react-router-dom";

import LandingPage from "./pages/LandingPage";

import Dashboard from "./pages/Dashboard";
import Analytics from "./pages/Analytics";
import Prediction from "./pages/Prediction";
import Recommendations from "./pages/Recommendations";
import Settings from "./pages/Settings";
import AddActivity from "./pages/AddActivity";

import Login from "./pages/Login";
import Register from "./pages/Register";

function ProtectedRoute({ children }) {

  const token =
    localStorage.getItem("token");

  return token
    ? children
    : <Navigate to="/login" />;
}

export default function App() {

  return (

    <BrowserRouter>

      <Routes>

        <Route
          path="/"
          element={<LandingPage />}
        />

        <Route
          path="/login"
          element={
            localStorage.getItem("token")

              ? <Navigate to="/dashboard" />

              : <Login />
          }
        />

        <Route
          path="/register"
          element={
            localStorage.getItem("token")

              ? <Navigate to="/dashboard" />

              : <Register />
          }
        />

        <Route
          path="/dashboard"
          element={
            <ProtectedRoute>

              <Dashboard />

            </ProtectedRoute>
          }
        />

        <Route
          path="/analytics"
          element={
            <ProtectedRoute>

              <Analytics />

            </ProtectedRoute>
          }
        />

        <Route
          path="/prediction"
          element={
            <ProtectedRoute>

              <Prediction />

            </ProtectedRoute>
          }
        />

        <Route
          path="/recommendations"
          element={
            <ProtectedRoute>

              <Recommendations />

            </ProtectedRoute>
          }
        />

        <Route
          path="/add-activity"
          element={
            <ProtectedRoute>

              <AddActivity />

            </ProtectedRoute>
          }
        />

        <Route
          path="/settings"
          element={
            <ProtectedRoute>

              <Settings />

            </ProtectedRoute>
          }
        />

      </Routes>

    </BrowserRouter>
  );
}