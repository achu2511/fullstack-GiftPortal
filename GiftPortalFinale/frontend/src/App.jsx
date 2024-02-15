// import CardSlider from "./components/cat"
// import Categories from "./pages/categories"
// import Home from "./pages/home"
// import Login from "./pages/login"

// import Profile from "./pages/Profile"
import  { useState } from 'react';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import Home from "./pages/home";
import Categories from "./pages/categories";
import Login from "./pages/Login";
import Product from "./pages/Product";
import UserProfile from "./pages/Profile";
// import Dashboard from "./pages/Admin";
import TotalEnq from "./pages/totalEnq";
import Cart from './pages/Cart';
import { Dashboard } from './admin/pages/Dashboard';
import { Tables } from './admin/pages/Tables';
import { Profile } from './admin/pages/Profile';
import AddGifts from './adminBack/addgifts';
import AddTheme from './adminBack/Addtheme';
// import Product from "./pages/Product"



function App() {

  const [imageName, setImageName] = useState('');
  const handleImageClick = (name) => {
    setImageName(name);
  };

  const [giftId,setGiftId]=useState('');
  const handleGiftId = (giftId) => {
    setGiftId(giftId);
  };
  const router = createBrowserRouter([  
    {
      path:'/',
      element:<Home  onImageClick={handleImageClick} />
    },
    {
      path:'/Categories',
      element:<Categories  imageName={imageName}  onImageClickProd={handleGiftId}/>
    },
    {
      path:'/login',
      element:<Login/>
    },
    {
      path:'/product',
      element:<Product giftId={giftId}/>
    },
    {
      path:'/profile',
      element:<UserProfile/>
    },
    {
      path:'/view/enquiry',
      element:<TotalEnq/>
    },
    {
      path:'/admin',
      element:<Dashboard/>
    },{
      path:'/Cart',
      element:<Cart/>
    },{
      path:'/tables',
      element:<Tables/>
    },{
      path:'/pppprofile',
      element:<Profile/>
    },{
      path:"/Dashboard",
      element:<Dashboard/>
    },{
      path:"/addGifts",
      element:<AddGifts/>
    },{
      path:"/addThemes",
      element:<AddTheme/>
    }
  ])

  return (
    <>
      <RouterProvider router={router}/>
    </>
  )
}

export default App
