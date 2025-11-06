const charts = document.getElementById('charts');
const N = 8;
const bars = [];

for (let i = 0; i < N; i++) {
  const wrap = document.createElement('div');
  wrap.className = 'bar';
  const fill = document.createElement('span');
  wrap.appendChild(fill);
  charts.appendChild(wrap);
  bars.push(fill);
  const label = document.createElement('div');
  label.className = 'label';
  label.textContent = 'Node ' + (i + 1);
  charts.appendChild(label);
  label.style.gridColumn = 'span 3';
}

function tick(){
  bars.forEach((el, i) => {
    const val = 20 + Math.abs(80 * Math.sin((Date.now()/700) + i));
    el.style.height = val + '%';
  });
  requestAnimationFrame(tick);
}
tick();
