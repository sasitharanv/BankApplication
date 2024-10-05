import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "../Pages/Home";
import DashBoard from "../Pages/DashBoard";

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Home></Home>} />
        <Route exact path="/Dashboard" element={<DashBoard></DashBoard>} />
      </Routes>
    </BrowserRouter>
  );
};
export default AppRoutes;
