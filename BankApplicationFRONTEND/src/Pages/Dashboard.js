
import * as React from "react";
import Box from "@mui/material/Box";
import WalletIcon from "@mui/icons-material/Wallet";
import Typography from "@mui/material/Typography";

import Container from "@mui/material/Container";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import Tooltip from "@mui/material/Tooltip";
import MenuItem from "@mui/material/MenuItem";
import AdbIcon from "@mui/icons-material/Adb";
import ResponsiveAppBar from "../components/ResponsiveAppBar";
import ImageSlider from "../components/ImageSlider ";
import smartpay from "../Images/online-banking-smaprtpay.png";
import { FormControl, Input, InputLabel, TextField } from "@mui/material";
import { useNavigate } from "react-router-dom";
import axios from "axios";
function DashBoard(){
  const [userDetails, setUserDetails] = React.useState(null);

  React.useEffect(() => {
      const token = localStorage.getItem('token');

      axios.get('http://localhost:8080/api/auth/user', {
          headers: {
              'Authorization': `Bearer ${token}`, 
          }
      })
      .then(response => setUserDetails(response.data))
      .catch(error => console.error('Error fetching user details:', error));
  }, []);

    return(
        <Container maxWidth="sm" sx={{ padding: "0px" }}>
        <Container
          maxWidth="sm"
          sx={{
            display: "flex",
            margin: "auto",
            margin: "20px 0px",
            padding: "0px",
          }}
        >
        </Container>
        <Container sx={{ display: "flex", justifyContent: "space-between" }}>
          <Typography
            sx={{
              fontSize: "50px",
              fontFamily: "Poppins",
              fontWeight: 800,
              alignItems: "center",
            }}
          >
            Hello {userDetails && userDetails.username}
          </Typography>
          <img src={smartpay}></img>
        </Container>
 
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            margin: "20px 24px",
          }}
        >
          <Typography
            sx={{
              fontSize: "16px",
              textDecoration: "underline",
              fontWeight: "500",
            }}
          >
            Unlock User
          </Typography>
          <Typography
            sx={{
              fontSize: "16px",
              textDecoration: "underline",
              fontWeight: "500",
            }}
          >
            Forgot passcode{" "}
          </Typography>
        </div>
  
        <div
          style={{
            display: "flex",
            justifyContent: "space-between",
            margin: "20px 24px",
          }}
        >
          <div>
            <Typography
              sx={{
                fontSize: "16px",
                fontWeight: "600",
              }}
            >
              New to online banking?{" "}
            </Typography>
            <Button
              style={{
                background: "#FFF",
                width: "100%",
                margin: "5px 0px",
                color: "#000",
                borderRadius: "8px",
                padding: "5px 0px",
                textTransform: "capitalize",
                fontWeight: 500,
                fontSize: "16px",
                border: "2px solid #FFCD00 ",
              }}
            >
              Register
            </Button>
          </div>
          <div>
            <Typography
              sx={{
                fontSize: "16px",
                fontWeight: "600",
              }}
            >
              Login to Wallet:{" "}
            </Typography>
            <div>
              <Button
                style={{
                  display: "flex",
                  background: "#FFCD00",
                  margin: "5px 0px",
                  width: "100%",
                  color: "#000",
                  borderRadius: "8px",
                  alignItems: "center",
                  gap: "4px",
                  width: "145px",
                  padding: "5px 0px",
                  textTransform: "capitalize",
                  fontWeight: 500,
                  fontSize: "16px",
                  color: "#000",
                }}
              >
                Smart pay <WalletIcon />
              </Button>
            </div>
          </div>
        </div>
      </Container>
    );
}
export default DashBoard;
