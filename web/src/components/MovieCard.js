import "../styles/MovieCard.css";

const MovieCard = (props) => {
  console.log(props);
  const { title, coverUrl, year, genre, duration, movieUrl } = props.movieInfo;
  return (
    <a href={movieUrl} target="_blank" className="movieCard">
      <img src={coverUrl} className="movieCard_image" />
      <h1 className="movieCard_title">{title}</h1>
      <p className="movieCard_info">{year}</p>
      <p className="movieCard_info">{genre}</p>
      <p className="movieCard_info">{duration}</p>
    </a>
  );
};

export default MovieCard;
