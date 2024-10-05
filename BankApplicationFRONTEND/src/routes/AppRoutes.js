import React from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from '../Pages/Home';
import Contact from '../Pages/Contact';
import Signup from '../Pages/Signup';
import LoginPage from '../Pages/LoginPage';
import Dashboard from '../Pages/Dashboard';





const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>

             
                <Route exact path="/" element={<Home></Home>} />
                <Route exact path="Login" element={<LoginPage></LoginPage>} />
                <Route exact path="Contact" element={<Contact></Contact>} />
                <Route exact path="Signup" element={<Signup></Signup>}/>
                <Route exact path="Dashboard" element={<Dashboard></Dashboard>}/>


            </Routes>
        </BrowserRouter>
    );
}
export default AppRoutes;