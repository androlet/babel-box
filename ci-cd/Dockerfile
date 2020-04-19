FROM openjdk:8
RUN groupadd -g 999 appuser && \
    useradd -r -u 999 -g appuser appuser;
RUN mkdir /home/appuser
USER appuser
WORKDIR /home/appuser
COPY babelbox.jar .
ENV db_address="localhost" db_port="5432" db_name="babelboxdev" db_username="thomas" db_password="sa" \
    jvm_options="" \
    spring_options="--spring.profiles.active=server,deployed"

CMD java $jvm_options -jar babelbox.jar $spring_options