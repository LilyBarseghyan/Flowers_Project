FROM nginx
LABEL maintainer="Lily Barseghyan"
COPY . /usr/share/nginx/html
CMD ["/usr/sbin/nginx", "-g", "daemon off;"]
