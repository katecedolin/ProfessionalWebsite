// Sample data from your CLI run
const inputNote = `
Patient: John Doe
DOB: 01/02/1980
Seen on: 2024-12-31
Phone: (555) 123-4567
Email: john.doe@hospital.org
MRN: A12B3C4
Narrative: Patient reports mild headache since Jan 5, 2024.
`;

const redactedNote = `
Patient: [NAME]
DOB: [DATE]
Seen on: [DATE]
Phone: (555) 123-4567
Email: [EMAIL]
[ID]
Narrative: Patient reports mild headache since [DATE].
`;

const reportJson = {
  "file": "data/sample_1.txt",
  "counts": { "DATE": 3, "ID": 1, "EMAIL": 1, "NAME": 1, "PHONE": 0 },
  "chars_redacted": 72
};

// Populate the DOM
document.getElementById('input-note').textContent = inputNote.trim();
document.getElementById('redacted-note').textContent = redactedNote.trim();
document.getElementById('report-json').textContent = JSON.stringify(reportJson, null, 2);
