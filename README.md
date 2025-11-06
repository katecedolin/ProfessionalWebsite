# Portfolio Site (Multi-file Structure)

A clean, easily-expandable version of the single-file portfolio site. Everything is organized into separate HTML, CSS, and JS files.

## Structure

```
portfolio-site/
├─ index.html
├─ css/
│  ├─ base.css
│  ├─ layout.css
│  ├─ components.css
│  ├─ utilities.css
│  └─ themes.css
├─ js/
│  └─ main.js
└─ assets/
   └─ images/
```

## Getting Started

Open `index.html` in your browser. No build step required.

### Customize

- Update copy in `index.html`.
- Add/modify styles in `css/*.css` files (small, focused layers).
- Add JS modules or components under `js/` and import them from `main.js` if you split further.
- Put images and icons in `assets/images/` and reference them as `./assets/images/...`.

### Notes

- Dark theme preference persists via `localStorage` (`theme` key).
- IntersectionObserver reveals elements on scroll (`.reveal` class).
- Year in footer auto-updates.
