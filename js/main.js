// Reveal on scroll
const io = new IntersectionObserver((entries)=>{
  entries.forEach((e)=>{
    if(e.isIntersecting){
      e.target.classList.add('is-visible');
      io.unobserve(e.target);
    }
  });
}, { threshold: .15 });

document.querySelectorAll('.reveal').forEach(el=> io.observe(el));

// Dark mode toggle with persistence
const body = document.body;
const toggleBtn = document.getElementById('themeToggle');
const storedTheme = localStorage.getItem('theme');

if (storedTheme === 'dark') {
  body.classList.add('dark');
  if (toggleBtn) toggleBtn.setAttribute('aria-pressed', 'true');
}

function setTheme(isDark){
  body.classList.toggle('dark', isDark);
  localStorage.setItem('theme', isDark ? 'dark' : 'light');
  if (toggleBtn) toggleBtn.setAttribute('aria-pressed', String(isDark));
}

if (toggleBtn) {
  toggleBtn.addEventListener('click', () => {
    setTheme(!body.classList.contains('dark'));
  });
}

// Current year
const yearEl = document.getElementById('year');
if (yearEl) yearEl.textContent = new Date().getFullYear();

// Make project cards clickable
document.querySelectorAll(".project").forEach(card => {
  const link = card.getAttribute("data-link");
  if (link) {
    card.style.cursor = "pointer";
    card.addEventListener("click", () => {
      window.open(link, "_blank"); // opens in new tab
    });
  }
});

