/* ReactJS components.. */

function Jot(props) {
    return <div className="card">
        <div class="card-block">
            <h4 class="card-title">{props.title}</h4>
            <h6 class="card-subtitle text-muted">{props.summary}</h6>
        </div>
        <img src="http://lorempixel.com/g/150/100/business" alt="Sample image"/>
        <div class="card-block">
            <p class="card-text">{props.village}</p>
        </div>
    </div>;
}
