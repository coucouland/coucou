/* ReactJS components.. */

function Jot(props) {
    return <div className="card">
        <div className="card-block">
            <h4 className="card-title">{props.title}</h4>
            <p className="card-text">{props.summary}</p>
            <p className="card-text"><small className="text-muted">Last updated {moment(props.pubDate).fromNow()}</small></p>
        </div>
        <div className="card-footer">
            {props.village}
        </div>
    </div>;
}
