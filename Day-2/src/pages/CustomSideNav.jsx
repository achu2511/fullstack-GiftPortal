import  { useState } from 'react';
import { Sidenav, Nav, Toggle, Modal, Button } from 'rsuite';
import DashboardIcon from '@rsuite/icons/legacy/Dashboard';
import GroupIcon from '@rsuite/icons/legacy/Group';
import MagicIcon from '@rsuite/icons/legacy/Magic';
import GearCircleIcon from '@rsuite/icons/legacy/GearCircle';
import '../css/sidenav.css'
import ExitIcon from '@rsuite/icons/Exit';
import { NavLink } from 'react-router-dom';

const CustomSideNav = () => {
  const [expanded, setExpanded] = useState(true);
  const [activeKey, setActiveKey] = useState('1');
  const [showZoomModal, setShowZoomModal] = useState(false);

  const adminInfo = {
    name: 'Admin User',
    imageUrl: 'https://imgs.search.brave.com/CM2KI4R3Gq4h2d_iPWAGbpSM-6fsWqXow1YlzHEsOkY/rs:fit:500:0:0/g:ce/aHR0cHM6Ly9pbWcu/ZnJlZXBpay5jb20v/ZnJlZS1waG90by9j/YXQtbG9va2luZy1m/b3J3YXJkXzExMjIt/MjIzNC5qcGc_c2l6/ZT02MjYmZXh0PWpw/Zw', // Replace with the actual image URL
  };

  const handleZoomClick = () => {
    setShowZoomModal(true);
  };

  const closeModal = () => {
    setShowZoomModal(false);
  };

  return (
    <div className="custom-sidenav-container">
      <div className="admin-info">
        <img
          src={adminInfo.imageUrl}
          alt="Admin"
          className="admin-image"
          onClick={handleZoomClick}
        />
        <div className="admin-name">{adminInfo.name}</div>
      </div>
      <Toggle
        onChange={setExpanded}
        checked={expanded}
        checkedChildren="Expand"
        unCheckedChildren="Collapse"
      />
      <hr />
      <Sidenav
        appearance="subtle"
        expanded={expanded}
        defaultOpenKeys={['3', '4']}
        style={{ backgroundColor: '#1d1d1d', color: 'white' }}
      >
        <Sidenav.Body style={{ backgroundColor: '#1d1d1d', color: 'white' }}>
          <Nav activeKey={activeKey} onSelect={setActiveKey}>
            <Nav.Item eventKey="1" icon={<DashboardIcon />}>
            <NavLink to={'/dashboard'}>Dashboard</NavLink>
            </Nav.Item>
            <Nav.Item eventKey="2" icon={<GroupIcon />}>
              <NavLink to={'/team'}>Manage Users</NavLink>
            </Nav.Item>
            <Nav.Menu placement="rightStart" eventKey="3" title="Products" icon={<MagicIcon />}>
              <Nav.Item eventKey="3-1"><NavLink to={'/postcourse'}>Post Products</NavLink></Nav.Item>
              <Nav.Item eventKey="3-2"><NavLink to={'/deletecourse'}>Delete Products</NavLink></Nav.Item>
              <Nav.Item eventKey="3-3"><NavLink to={'/editcourse'}>Edit Products</NavLink></Nav.Item>
              <Nav.Item eventKey="3-4"><NavLink to={'/view'}>View Products</NavLink></Nav.Item>
            </Nav.Menu>
            <Nav.Menu placement="rightStart" eventKey="4" title="Enquires" icon={<GearCircleIcon />}>
              <Nav.Item eventKey="4-1"><NavLink to={'/view/enquiry'}>View All Enquires</NavLink></Nav.Item>
              {/* <Nav.Item eventKey="4-3">Versions</Nav.Item> */}
              <Nav.Menu eventKey="4-5" title="Payment">
                <Nav.Item eventKey="4-5-1"> <NavLink to={'/transaction'}>View All Transaction</NavLink></Nav.Item>
                <Nav.Item eventKey="4-5-2">Revenue Generated</Nav.Item>
              </Nav.Menu>
            </Nav.Menu>
              <Nav.Item eventKey="1" icon={<ExitIcon />}>
            <NavLink to={'/login'}>Logout</NavLink>
            </Nav.Item>
          </Nav>
        </Sidenav.Body>
      </Sidenav>

      {/* Zoom Modal */}
      <Modal show={showZoomModal} onHide={closeModal}>
        <Modal.Header>
          <Modal.Title>Zoomed Image</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <img src={adminInfo.imageUrl} alt="Admin" style={{ width: '100%' }} />
        </Modal.Body>
        <Modal.Footer>
          <Button onClick={closeModal} appearance="primary">
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
};

export default CustomSideNav;