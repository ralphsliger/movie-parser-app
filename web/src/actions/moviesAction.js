//const URL = "https://prueba-api-movies.herokuapp.com/api/getAll";
const URL = "http://localhost:8080/api/library"

export const getMovies = () => {
  return (dispatch) => {
    window
      .fetch(URL)
      .then((response) => response.json())
      .then((data) => {
        dispatch(moviesList(data));
      })
      .catch((e) => {
        console.log(e);
      });
  };
};

export const moviesList = (movies) => ({
  type: "MOVIES_LIST",
  payload: movies
});
