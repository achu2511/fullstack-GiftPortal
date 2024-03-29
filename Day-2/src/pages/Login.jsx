import '../css/Login.scss'
import { useEffect } from 'react';
import $ from 'jquery';
const Login = () => {
    useEffect(() => {
        // Your jQuery code goes here
        $('#signup').click(function () {
          $('.pinkbox').css('transform', 'translateX(80%)');
          $('.signin').addClass('nodisplay');
          $('.signup').removeClass('nodisplay');
        });
    
        $('#signin').click(function () {
          $('.pinkbox').css('transform', 'translateX(0%)');
          $('.signup').addClass('nodisplay');
          $('.signin').removeClass('nodisplay');
        });
      }, []); // Empty dependency array ensures that this effect runs once after the initial render
    
    return (
      <>
        <div className="container">
          <div className="welcome">
            <div className="pinkbox">
              <div className="signup nodisplay">
                <h1>register</h1>
                <form autoComplete="off">
                  <input type="text" placeholder="username" />
                  <input type="email" placeholder="email" />
                  <input type="password" placeholder="password" />
                  <input type="password" placeholder="confirm password" />
                  <button className="button submit">create account </button>
                </form>
              </div>
              <div className="signin">
                <h1>sign in</h1>
                <form className="more-padding" autoComplete="off">
                  <input type="text" placeholder="username" />
                  <input type="password" placeholder="password" />
                  <div className="checkbox">
                    <input type="checkbox" id="remember" />
                    <label htmlFor="remember">remember me</label>
                  </div>
                  <button className="button submit">login</button>
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
              <button className="button" id="signin">
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
              <button className="button" id="signup">
                sign up
              </button>
            </div>
          </div>
        </div>
      </>
    );
  };
  
  export default Login;
  