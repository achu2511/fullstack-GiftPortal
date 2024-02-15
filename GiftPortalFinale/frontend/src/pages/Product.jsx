// ... (previous imports)
import { useEffect, useState } from "react";
import axios from "axios";
import CustNav from "../components/custNav";
import { Button, ButtonToolbar } from 'rsuite';
import PropTypes from 'prop-types';
import '../App.css';

const Product = ({ giftId }) => {
  const [userData, setUserData] = useState({});
  const [themes, setThemes] = useState([]);
  const [selectedAddon, setSelectedAddon] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [cartStatus, setCartStatus] = useState('notAdded');
  const [themeStatus, setThemeStatus] = useState('notSelected');
  const tok = window.localStorage.getItem('token');

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(`http://localhost:8181/api/admin/getpariculargift/${giftId}`, {
          headers: {
            Authorization: `Bearer ${tok}`,
          },
        });

        setUserData(response.data);
      } catch (error) {
        console.error("Error fetching user data:", error);
      }
    };

    fetchData();
  }, [giftId, tok]);

  useEffect(() => {
    axios
      .get("http://localhost:8181/api/admin/get/theme", {
        headers: {
          Authorization: `Bearer ${tok}`,
        },
      })
      .then(response => {
        console.log(response.data);
        setThemes([...response.data]);
      })
      .catch(error => {
        console.log(error);
      });
  }, [tok]);

  const handleAddToCart = async () => {
    if (selectedAddon !== null) {
      try {
        // Replace this URL with the actual endpoint to handle the cart addition
        const response = await axios.post(`http://localhost:8181/api/${giftId}/${selectedAddon}/${quantity}`, {
        }, {
          headers: {
            Authorization: `Bearer ${tok}`,
          },
        });

        console.log("Addon added to cart:", response.data);
        setCartStatus('added'); // Update the cart status on successful addition
      } catch (error) {
        alert('Product already exists in the cart');
        console.error("Error adding addon to cart:", error);
        // Add any error handling logic here
      }
    }
  };

  const handleIncreaseQuantity = () => {
    setQuantity(prevQuantity => prevQuantity + 1);
  };

  return (
    <>
      <CustNav />
      <div className="prod-cont">
        <div className="prod-cont-img">
          {userData.imgUrl && (
            <img src={userData.imgUrl} alt="" className="prod-img" />
          )}
        </div>
        <div className="prod-cont-info">
          <div className="prod-info-head">
            {userData.name && (
              <h1 className="info-head">{userData.giftName}</h1>
            )}
          </div>
          <div className="prod-info-price">
            <h2>₹ {userData.giftPrice}</h2>
          </div>
          <div className="prod-desc">
            <h2 className="prod-desc-txt-head">Description</h2>
            {userData.giftDetails && (
              <p className="prod-desc-txt">{userData.giftDetails}</p>
            )}
          </div>
          <div className="prod-theme">
            <h1 className="prod-teme-head">Make it special</h1>
            <h2 className="prod-theme-sub">Gourmet-addons</h2>
            <div className="prod-addons">
              {themes.map(theme => (
                <div key={theme.themeId} className="prod-theme-slider">
                  <div className="prod-theme-slider-card">
                    <img src={theme.themeimgurl} alt="" className="prod-theme-img" />
                    <p className="prod-theme-txt">{theme.themeName}</p>
                    <p style={{ fontSize: '1vw' }}>₹ {theme.themePrice}</p>
                    <ButtonToolbar>
                      <Button
                        appearance={selectedAddon === theme.themeId ? 'ghost' : 'primary'}
                        style={{
                          borderColor: selectedAddon === theme.themeId ? 'red' : 'red',
                          color: selectedAddon === theme.themeId ? 'red' : 'white'
                        }}
                        onClick={() => {
                          setSelectedAddon(theme.themeId);
                          setThemeStatus('selected');
                        }}
                        disabled={selectedAddon !== null && selectedAddon !== theme.themeId}
                      >
                        {selectedAddon === theme.themeId ? 'Added' : 'Add'}
                      </Button>
                    </ButtonToolbar>
                  </div>
                </div>
              ))}
            </div>
            <div className="prod-btn">
              <ButtonToolbar>
                <Button
                  appearance={cartStatus === 'added' ? 'ghost' : 'primary'}
                  style={{
                    borderColor: cartStatus === 'added' ? 'red' : 'red',
                    color: cartStatus === 'added' ? 'red' : 'white'
                  }}
                  onClick={handleAddToCart}
                  disabled={selectedAddon === null || cartStatus === 'added'}
                >
                  {cartStatus === 'added' ? 'Added to Cart' : 'Add to Cart'}
                </Button>
              </ButtonToolbar>
              <ButtonToolbar>
                {/* Button to increase quantity */}
                <Button
                  appearance="default"
                  style={{ borderColor: 'blue', color: 'black', marginLeft: '5vw' }}
                  onClick={handleIncreaseQuantity}
                >
                  Increase Quantity
                </Button>
              </ButtonToolbar>
              <div>
                <p>Quantity: {quantity}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

Product.propTypes = {
  giftId: PropTypes.string.isRequired,
};

export default Product;
