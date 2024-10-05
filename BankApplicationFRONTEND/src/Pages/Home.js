import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import WalletIcon from "@mui/icons-material/Wallet";
import Typography from "@mui/material/Typography";
import Menu from "@mui/material/Menu";
import MenuIcon from "@mui/icons-material/Menu";
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

function Home() {
  const [email, setEmail] = React.useState("");
  const [password, setPassword] = React.useState("");
  const navigate = useNavigate();

  const handleChangePassword = (event) => {
    setPassword(event.target.value);
  };

  const handleChangeuserName = (event) => {
    setEmail(event.target.value);
  };
  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/api/auth/login",
        {
          usernameOrEmail: email,
          password: password,
        }
      );

      const { accessToken} = response.data;

      localStorage.setItem("token", ` ${accessToken}`);
      navigate("/Dashboard")
    } catch (error) {
      console.error("Login failed:", error);
    }
  };

  return (
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
        <ImageSlider />
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
          Hello<br></br> there!
        </Typography>
        <img src={smartpay}></img>
      </Container>
      <Box
        sx={{
          display: "flex",
          flexDirection: "column",
          gap: "40px",
          margin: "20px 24px",
        }}
      >
        <TextField
          variant="standard"
          fullWidth
          label="Account No:*"
          onChange={handleChangeuserName}
        />

        <TextField
          onChange={handleChangePassword}
          variant="standard"
          fullWidth
          label="Passcode:*"
          type="password"
        />
      </Box>
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
      <Button
        onClick={handleLogin}
        style={{
          background: "#FFCD00",
          width: "90%",
          margin: "20px 24px",
          color: "#000",
          borderRadius: "8px",
          padding: "5px 0px",
          textTransform: "capitalize",
          fontWeight: 500,
          fontSize: "16px",
        }}
      >
        Login
      </Button>
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
export default Home;
