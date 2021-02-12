from diagrams import Diagram
from diagrams.aws.compute import Lambda
from diagrams.aws.engagement import SES
from diagrams.aws.integration import SNS
from diagrams.aws.network import APIGateway
from diagrams.generic.compute import Rack

with Diagram("Coucou, Let's Collaborate", show=False, direction="TB"):

    cocuou = Rack("Coucou App")

    # APIGateway("calendar user agent api") >> cua

    events = APIGateway("calendar events")
    tasks = APIGateway("calendar tasks")
    journal = APIGateway("calendar journal")
    availability = APIGateway("calendar availability")
    subscribers = APIGateway("subscriber list")
    userprofile = APIGateway("user profiles")

    userprofile >> cocuou >> userprofile
    events >> cocuou >> events
    tasks >> cocuou >> tasks
    journal >> cocuou >> journal
    availability >> cocuou >> availability
    subscribers >> cocuou >> subscribers

    topic = SNS("calendar topic")
    cua = Lambda("calendar user agent")

    [userprofile, subscribers] >> cua
    [events, tasks, journal, availability] >> topic >> cua >> topic

    ses = SES("simple email service")
    topic >> Lambda("calendar mail send") >> SES("smtp server")
    ses >> SNS("ses message topic") >> Lambda("calendar mail receive") >> topic
