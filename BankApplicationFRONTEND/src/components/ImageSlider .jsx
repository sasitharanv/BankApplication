import React, { useState, useEffect } from "react";
import { Grid, IconButton } from "@mui/material";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import ArrowForwardIosIcon from "@mui/icons-material/ArrowForwardIos";

const ImageSlider = () => {
  const images = [
    "https://s3.ap-southeast-1.amazonaws.com/static.boc.lk/3959/media-230227042308.jpg",
    "https://s3.ap-southeast-1.amazonaws.com/static.boc.lk/6725/media-240814092255.jpg",
    "https://s3.ap-southeast-1.amazonaws.com/static.boc.lk/6815/media-240918061634.jpg",
  ];

  const [currentIndex, setCurrentIndex] = useState(0);

  // Function to go to the next image
  const nextImage = () => {
    setCurrentIndex((prevIndex) => (prevIndex + 1) % images.length);
  };

  // Function to go to the previous image
  const prevImage = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex === 0 ? images.length - 1 : prevIndex - 1
    );
  };

  // Auto-slide functionality with 3 seconds delay
  useEffect(() => {
    const interval = setInterval(() => {
      nextImage();
    }, 3000);
    return () => clearInterval(interval); // Clear interval on component unmount
  }, []);

  return (
    <div
      style={{ display: "flex", alignItems: "center", position: "relative" }}
    >
      <IconButton onClick={prevImage} sx={{ position: "fixed", left: 20,color:"#FFF" }}>
        <ArrowBackIosNewIcon />
      </IconButton>

      {/* Display only one image */}
      <img
        src={images[currentIndex]}
        alt={`Slide ${currentIndex + 1}`}
        style={{ width: "100%", height: "375px" }}
      />

      <IconButton onClick={nextImage} sx={{ position: "fixed", right: 20,color:"#FFF" }}>
        <ArrowForwardIosIcon />
      </IconButton>
    </div>
  );
};

export default ImageSlider;
