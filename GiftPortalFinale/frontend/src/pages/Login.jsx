  // Import necessary libraries and styles
  import  { useState, useEffect } from 'react';
  import $ from 'jquery'; // Import jQuery library
  import '../css/Login.scss'; // Import your CSS file
  import axios from 'axios';
// import { Navigate } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { Co2Sharp } from '@mui/icons-material';

  // import jwtDecode from "jwt-decode";

  // Define the Login component
  const Login = () => {
    const navigate = useNavigate();
    // State for signup form data
    const [signupData, setSignupData] = useState({
      username: '',
      email: '',
      password: '',
    });

    // State for signin form data
    const [signinData, setSigninData] = useState({
      email: '',
      password: '',
    });




    // State for signup form error message
    const [errorSignup, setErrorSignup] = useState('');

    // State for signin form error message
    const [errorSignin, setErrorSignin] = useState('');

    // Function to handle changes in signup form inputs
    const handleSignupChange = (e) => {
      setSignupData({ ...signupData, [e.target.name]: e.target.value });
    };

    // Function to handle changes in signin form inputs
    const handleSigninChange = (e) => {
      setSigninData({ ...signinData, [e.target.name]: e.target.value });
    };

    // Function to handle signup form submission
    const handleSignupSubmit = (e) => {
      e.preventDefault();
      // Perform validation
      if (
        signupData.username.trim() === '' ||
        signupData.email.trim() === '' ||
        signupData.password.trim() === '' ||
        signupData.confirmPassword.trim() === ''
      ) {
        setErrorSignup('Please fill in all fields');
        return;
      }
      if (signupData.password.length < 8) {
        setErrorSignup('Password must be at least 8 characters long');
        return;
      }
      if (signupData.password !== signupData.confirmPassword) {
        setErrorSignup('Passwords do not match');
        return;
      }

      console.log('Signup form submitted:', signupData);
    };

    // Function to handle signin form submission
    const handleSigninSubmit = (e) => {
      e.preventDefault();
      // Perform validation
      if (signinData.email.trim() === '' || signinData.password.trim() === '') {
        setErrorSignin('Please fill in all fields');
        return;
      }

      console.log('Signin form submitted:', signinData);
    };

    // useEffect to set up click event handlers using jQuery
    useEffect(() => {
      // Click event for signup button
      $('#signup').click(function () {
        setErrorSignup(''); // Clear previous error message
        $('.pinkbox').css('transform', 'translateX(80%)');
        $('.signin').addClass('nodisplay');
        $('.signup').removeClass('nodisplay');
      });

      // Click event for signin button
      $('#signin').click(function () {
        setErrorSignin(''); // Clear previous error message
        $('.pinkbox').css('transform', 'translateX(0%)');
        $('.signup').addClass('nodisplay');
        $('.signin').removeClass('nodisplay');
      });
    }, []);

    const handleLogin = async () => {
      try {
        if (signinData.email.trim() === '' || signinData.password.trim() === '') {
          setErrorSignin('Please provide both username and password.');
          return;
        }
    
        const body = { email: signinData.email, password: signinData.password };
    
        // Make the login request to the backend
        const response = await axios.post('http://localhost:8181/api/v1/auth/login', body);
        console.log(response.data)
        console.log("response")
        if (!response.data.token) {
          throw new Error('Invalid username or password');
        }
    
        const token = response.data.token;
        
        // Set token in sessionStorage
        window.localStorage.setItem('token', token);
    
        // Fetch user data using the obtained token
        const userResponse = await axios.get(`http://localhost:8181/api/GETUSERNAME/${signinData.email}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
    
        console.log(userResponse.data);
        window.localStorage.setItem('email', userResponse.data);
    
        // Redirect to the desired page (replace 'navigate("/")' with your actual navigation logic)
        navigate("/");
    
      } catch (error) {
        setErrorSignin(error.message);
      }
    };
    

    const handleRegister = async () => {
      try {
        if (
          signupData.username.trim() === '' ||
          signupData.email.trim() === '' ||
          signupData.password.trim() === '' ||
          signupData.confirmPassword.trim() === ''
        ) {
          setErrorSignup('Please fill in all fields');
          return;
        }
        if (signupData.password.length < 8) {
          setErrorSignup('Password must be at least 8 characters long');
          return;
        }
        if (signupData.password !== signupData.confirmPassword) {
          setErrorSignup('Passwords do not match');
          return;
        }

        const response = await fetch('http://localhost:8181/api/v1/auth/register', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(signupData),
        });

        if (!response.ok) {
          throw new Error('Failed to sign up');
        }

        console.log('Signup successful');
        setErrorSignup('Signup successful');
        alert("registration successfull");
        location.reload();
      } catch (error) {
        setErrorSignup('Failed to sign up. Please try again later.');
      }
    };

    return (
      <>
        <div className="log-div-bg">
          <div className="container">
            <div className="welcome">
              {/* Pinkbox container for signup and signin forms */}
              <div className="pinkbox">
                <div className="signup nodisplay">
                  <h1>register</h1>
                  {errorSignup && <p className="error-message">{errorSignup}</p>}
                  <form autoComplete="off" onSubmit={handleSignupSubmit}>
                    <input
                      type="text"
                      placeholder="username"
                      name="username"
                      value={signupData.username}
                      onChange={handleSignupChange}
                    />
                    <input
                      type="email"
                      placeholder="email"
                      name="email"
                      value={signupData.email}
                      onChange={handleSignupChange}
                    />
                    <input
                      type="password"
                      placeholder="password"
                      name="password"
                      value={signupData.password}
                      onChange={handleSignupChange}
                    />
                    <input
                      type="password"
                      placeholder="confirm password"
                      name="confirmPassword"
                      value={signupData.confirmPassword}
                      onChange={handleSignupChange}
                    />
                    <button className="button submit" type="submit" onClick={handleRegister}>
                      create account
                    </button>
                  </form>
                </div>
                {/* Signin form */}
                <div className="signin">
                  <h1>sign in</h1>
                  {errorSignin && <p className="error-message">{errorSignin}</p>}
                  <form className="more-padding" autoComplete="off" onSubmit={handleSigninSubmit}>
                    <input
                      type="text"
                      placeholder="email"
                      name="email"
                      value={signinData.email}
                      onChange={handleSigninChange}
                    />
                    <input
                      type="password"
                      placeholder="password"
                      name="password"
                      value={signinData.password}
                      onChange={handleSigninChange}
                    />
                    <div className="checkbox">
                      <input type="checkbox" id="remember" />
                      <label htmlFor="remember">remember me</label>
                    </div>
                    <button className="button submit" type="submit" onClick={handleLogin}>
                      login
                    </button>
                  </form>
                </div>
              </div>
              <div className="leftbox">
                <h2 className="title">
                  <span>BLOOM</span>&<br />BOUQUET
                </h2>
                <p className="desc">pick your perfect <span>bouquet</span></p>
                <img
                  className="flower smaller"
                  src="https://image.ibb.co/d5X6pn/1357d638624297b.jpg"
                  alt="1357d638624297b"
                  border="0"
                />
                <p className="account">have an account?</p>
                <button className="button" id="signin" onClick={handleLogin}>
                  login
                </button>
              </div>
              <div className="rightbox">
                <h2 className="title">
                  <span>BLOOM</span>&<br />BOUQUET
                </h2>
                <p className="desc"> pick your perfect <span>bouquet</span></p>
                <img
                  className="flower"
                  src="https://preview.ibb.co/jvu2Un/0057c1c1bab51a0.jpg"
                  alt="flower"
                />
                <p className="account">don't have an account?</p>
                <button className="button" id="signup" onClick={handleRegister}>
                  sign up
                </button>
              </div>
            </div>
          </div>
        </div>
      </>
    );
  };

  // Export the Login component
  export default Login;
