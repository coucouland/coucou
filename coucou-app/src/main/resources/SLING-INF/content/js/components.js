/* ReactJS components.. */

function Jot(props) {
    return <div className="card">
        <div className="card-block">
            <h4 className="card-title">{props.title} {moment(props.pubDate).fromNow()}</h4>
            <h6 className="card-subtitle text-muted">{props.summary}</h6>
        </div>
        <div className="card-block">
            <p className="card-text">{props.village}</p>
        </div>
    </div>;
}
