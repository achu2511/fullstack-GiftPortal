// import CustomSideNav from "../components/CustomSideNav";

// import '../assets/css/dashboard.css'; 
// import { Bar } from "react-chartjs-2";
import '../css/admin.css';
import CustomSideNav from './CustomSideNav';

const Dashboard = () => {
    const coursesPurchasedData = [10, 15, 8, 12, 20, 18, 25];
  const enquiriesData = [5, 8, 12, 10, 15, 7, 11];
  const revenueData = [500, 800, 1200, 1000, 1500, 700, 1100];
    const chartOptions = {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      };
  return (
    <div>
<CustomSideNav/>
    <div className="dashboard-container">
      <div className="dashboard-card">
        <h4 className="dashboard-courses">No of pr</h4>
        <br/>
        <br/>
        <p>25</p>
      </div>

      <div className="dashboard-card">
        <h4 className="dashboard-courses">Profit</h4>
        <br/>
        <br/>
        <p>10</p>
      </div>

      <div className="dashboard-card">
        
        <h4 className="dashboard-courses">Sold Items</h4>
        <br/>
        <br/>
        <p>100</p>
      </div>

      <div className="dashboard-card">
      <h4 className="dashboard-revenue">Revenue Generated</h4>
      <br/>
        {/* <br/> */}
        <p>$5,000</p>
      </div>

      {/* <div className="dashboard-card">
        <h2>No of Transactions</h2>
        <p>50</p>
      </div>

      <div className="dashboard-card">
        <h2>Recent Transactions</h2>
        <ul>
          <li>Transaction 1</li>
          <li>Transaction 2</li>
          <li>Transaction 3</li>
          
        </ul>
      </div> */}
      <br/>
      <div className="dahboard-image">

      <div className="image1-dash">
        <img src="https://imgs.search.brave.com/ShB_kZPCmA8NjXAjawREYCAJcPWeKYFqZLVzUbLyKrg/rs:fit:500:0:0/g:ce/aHR0cHM6Ly9pMi5j/ZG4udHVybmVyLmNv/bS9tb25leS9kYW0v/YXNzZXRzLzE2MDUx/ODE1MzI1OS1jaGFy/dC1jb2xsZWdlLWVu/cm9sbG1lbnQtaXMt/ZmFsbGluZy03ODB4/NDM5LmpwZw"></img>
      </div>
      <div className="image2-dash">
        <img src="https://imgs.search.brave.com/vctkmLMv6tPthEr-9e_tj893BsFyhQFDvalUTdgA6aA/rs:fit:500:0:0/g:ce/aHR0cHM6Ly93d3cu/ZGF0YXBpbmUuY29t/L2Jsb2cvd3AtY29u/dGVudC91cGxvYWRz/LzIwMjAvMDcvc2Fs/ZXMtdGFyZ2V0LWRh/dGFwaW5lLnBuZw"></img>
      </div>
      </div>
      {/* <div><CourseChart/></div> */}
      {/* <div className="dashboard-graph">
        <h2>Courses Purchased (Monthly)</h2>
        <Bar data={{ labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'], datasets: [{ data: coursesPurchasedData, backgroundColor: 'rgba(75,192,192,0.6)', borderColor: 'rgba(75,192,192,1)', borderWidth: 1 }] }} options={chartOptions} />
      </div> */}

      {/* <div className="dashboard-graph">
        <h2>Enquiries (Monthly)</h2>
        <Bar data={{ labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'], datasets: [{ data: enquiriesData, backgroundColor: 'rgba(255,99,132,0.6)', borderColor: 'rgba(255,99,132,1)', borderWidth: 1 }] }} options={chartOptions} />
      </div>

      <div className="dashboard-graph">
        <h2>Revenue (Monthly)</h2>
        <Bar data={{ labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul'], datasets: [{ data: revenueData, backgroundColor: 'rgba(54, 162, 235, 0.6)', borderColor: 'rgba(54, 162, 235, 1)', borderWidth: 1 }] }} options={chartOptions} />
      </div> */}
    </div>
    </div>
  );
};

export default Dashboard;