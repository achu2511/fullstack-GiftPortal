import CustNav from "../components/custNav";
import PropTypes from 'prop-types';
import { useEffect, useState } from "react";
import axios from "axios";
import '../App.css';
import { Link } from "react-router-dom";

const Categories = ({ imageName,onImageClickProd }) => {
  const [prods, setProds] = useState([]);
  const [token, setToken] = useState(() => window.localStorage.getItem('token'));
  console.log("storedToken")
  console.log(token)


  const handleGiftId = (giftId) => {
    console.log(typeof(onImageClickProd));
    onImageClickProd(giftId);
  };

  useEffect(() => {

    axios
      .get(`http://localhost:8181/api/getProducts/${imageName}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then(response => {+
        console.log(response.data);
        setProds([...response.data]);
        console.log(prods);
      })
      .catch(error => {
        console.log(error);
      });
  }, [imageName,token]);

  return (
    <div>
      <CustNav />
      <h1 style={{ textAlign: 'center' }}>{imageName}</h1>
      {/* <h1>{prods}</h1> */}
      <div className="catp-cont">
        <div className="catp-card-grid">
          {prods.length > 0 ? (
            prods.map(prod => (
              <div key={prod.giftId} >
                <div className="caaaatp_card">
                <img className="catp-img" src={prod.imgUrl} alt={`Product: ${prod.giftName}`} />
                <h2 className="catp-img-txt">{prod.giftName}</h2>
                <p className="author">{prod.giftDetails}</p>
                <p className="p-price"><strong>Price : </strong>{prod.giftPrice}</p>
                <Link to={'/product'}><button className="g-btn"  onClick={() => handleGiftId(prod.giftId)}>Buy Now</button></Link>
                </div>
              </div>
            ))
          ) : (
            <p>No products available</p>
          )}
        </div>
      </div>
    </div>
  );
}

Categories.propTypes = {
  imageName: PropTypes.string.isRequired,
  onImageClickProd: PropTypes.string.isRequired, 
};

export default Categories;
