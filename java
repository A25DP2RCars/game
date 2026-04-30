let red =
document.getElementById("red");
let yellow =
document.getElementById("yellow");
let green =
document.getElementById("green");

let statusText =
document.getElementById("status");
let timeText =
document.getElementById("time");
let bestText =
document.getElementById("best");

let startTime = 0;
let waitingForGreen = false;
let gameStarted = false;

let bestScore =
localStorage.getItem("f1Best");
if (bestScore) {
    bestText.textContent = bestScore;
}

function resetLights() {
    red.classList.remove("active" ,
        "red");
        yellow.classList.remove("active" ,
        "yellow");
        green.classList.remove("active" ,
        "green");
}

function startGame() {
    resetLights();
    statusText.textContent =
    "Gatavojies...";
    gameStarted = true;
    waitingForGreen = false;

    setTimeout(() => {
        red.classList.add("active","red");
    }, 500);

     setTimeout(() => {
        yellow.classList.add("active","yellow");
    }, 1200);

     let randomDelay = Math.random() * 2000 + 2000;

      setTimeout(() => {
        green.classList.add("active","green");
    statusText.textContent = "SPIED TAGAD!";
    startTime = Date.now();
    waitingForGreen = true;
      }, randomDelay);
}

document.addEventListener("keydown", function(e) {
    if (e.code === "Space") {

        if (!gameStarted) {
            startGame();
            return;
        }

        if (!waitingForGreen) {
            statusText.textContent = "Pāragri! ❌";
            gameStarted = false;
            return;
        }

        let reactionTime = (Date.now() - startTime) / 1000;
        let formatted = reactionTime.toFixed(3);

        timeText.textContent = formatted;
        statusText.textContent = "Perfekts starts! 🏁";

        /* high score */
        if (!bestScore || reactionTime < bestScore) {
            bestScore = formatted;
            localStorage.setItem("f1Best", formatted);
            bestText.textContent = formatted;
        }

        gameStarted = false;
        waitingForGreen = false;
    }
});
