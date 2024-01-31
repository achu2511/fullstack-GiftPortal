// import CardSlider from "./components/cat"
// import Categories from "./pages/categories"
// import Home from "./pages/home"
// import Login from "./pages/login"

import Profile from "./pages/Profile"
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./pages/home";
import Categories from "./pages/categories";
import Login from "./pages/Login";
import Product from "./pages/Product";
import UserProfile from "./pages/Profile";
import Dashboard from "./pages/Admin";
// import Product from "./pages/Product"



function App() {
  const router = createBrowserRouter([  
    {
      path:'/',
      element:<Home/>
    },
    {
      path:'/Categories',
      element:<Categories/>
    },
    {
      path:'/login',
      element:<Login/>
    },
    {
      path:'/product',
      element:<Product/>
    },
    {
      path:'/profile',
      element:<UserProfile/>
    },
    {
      path:'/admin',
      element:<Dashboard/>
    }
  ])

  return (
    <>
      {/* <Login/> */}
      {/* <Home/> */}
      {/* <CardSlider/> */}
      {/* <Categories/> */}
      {/* <Product/> */}
      {/* <Profile/> */}
      <RouterProvider router={router}/>
    </>
  )
}

export default App
