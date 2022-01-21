import MovieCard from "./MovieCard";
import "../styles/MoviesList.css";

const MoviesList = ({ movies }) => {
  console.log(movies);

  return (
    <div className="moviesList">
      {movies[0] &&
        Object.keys(movies[0].movie).map((movie) => {
          return (
            <MovieCard
              className="patientCard"
              key={movie}
              movieInfo={movies[0].movie[movie]}
            />
          );
        })}
    </div>
  );
};

export default MoviesList;
