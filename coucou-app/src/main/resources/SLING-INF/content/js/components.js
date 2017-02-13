/* ReactJS components.. */

function Jot(props) {
    return <div className="card">
        <div class="card-block">
            <h4 class="card-title">{props.title} {moment(props.pubDate).fromNow()}</h4>
            <h6 class="card-subtitle text-muted">{props.summary}</h6>
        </div>
        <div class="card-block">
            <p class="card-text">{props.village}</p>
        </div>
    </div>;
}
