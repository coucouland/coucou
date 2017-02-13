/* ReactJS components.. */

function Jot(props) {
    return <div className="card">
        <div className="card-block">
            <h4 className="card-title">{props.title}</h4>
            <h6 className="card-subtitle text-muted">{props.village}</h6>
            <p className="card-text">{props.summary}</p>
        </div>
        <div className="card-footer">
            <small className="text-muted">Last updated {moment(props.pubDate).fromNow()}</small>
        </div>
    </div>;
}
