const apiUrl = '/api/developers';
const form = document.getElementById("devForm");
const expandBtn = document.getElementById("expandBtn");
const cancelBtn = document.getElementById("cancelBtn");

// 🌿 Toggle form visibility
expandBtn.addEventListener("click", () => {
  form.classList.toggle("expanded");
});
cancelBtn.addEventListener("click", () => {
  form.classList.remove("expanded");
});

// 🌿 Load all developers from backend
async function loadDevelopers() {
  try {
    const res = await fetch(apiUrl);
    if (!res.ok) throw new Error("Failed to fetch developers");
    const data = await res.json();

    const container = document.getElementById('developers');
    container.innerHTML = '';

    // Sidebar statistics
    document.getElementById('devCount').textContent = data.length;
    const avgSkills = data.length
      ? (data.reduce((acc, dev) => acc + ((dev.skills && dev.skills.length) || 0), 0) / data.length).toFixed(1)
      : '—';
    document.getElementById('avgSkills').textContent = avgSkills;
    const certCount = data.reduce((acc, dev) => acc + ((dev.certifications && dev.certifications.length) || 0), 0);
    document.getElementById('certCount').textContent = certCount || '—';

    // Developer cards
    data.forEach(dev => {
      const div = document.createElement('div');
      div.className = 'card';
      div.innerHTML = `
        <button class="remove-btn" onclick="deleteDeveloper(${dev.id}, this)">✕</button>
        <button class="edit-btn" onclick="editDeveloper(${dev.id})">✎</button>
        <div class="card-content">
          <h3>${dev.name}</h3>
          <p><strong>${dev.title || dev.role || '—'}</strong></p>
          <p><strong>Skills:</strong> ${(dev.skills && dev.skills.length) ? dev.skills.join(', ') : '—'}</p>
          <p><strong>Certifications:</strong> ${(dev.certifications && dev.certifications.length) ? dev.certifications.join(', ') : '—'}</p>
        </div>`;
      container.appendChild(div);
    });

    // ✅ Wait a tick before drawing charts
    setTimeout(() => updateCharts(data), 150);
  } catch (err) {
    console.error("Error loading developers:", err);
  }
}

// 🌿 Filter developers by skill text
document.getElementById('filterInput').addEventListener('input', e => {
  const search = e.target.value.toLowerCase();
  document.querySelectorAll('.card').forEach(card => {
    const text = card.textContent.toLowerCase();
    card.style.display = text.includes(search) ? 'flex' : 'none';
  });
});

// 🌿 Add new developer
async function addDeveloper(e) {
  e.preventDefault();

  const newDev = {
    name: document.getElementById('name').value,
    title: document.getElementById('title').value,
    skills: document.getElementById('skills').value.split(',').map(s => s.trim()).filter(Boolean),
    certifications: document.getElementById('certifications').value.split(',').map(c => c.trim()).filter(Boolean)
  };

  const submitBtn = form.querySelector("button[type='submit']");
  submitBtn.disabled = true;
  submitBtn.textContent = "Adding...";

  const res = await fetch(apiUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(newDev)
  });

  submitBtn.disabled = false;
  submitBtn.textContent = "Add Developer";

  if (res.ok) {
    form.reset();
    form.classList.remove("expanded");
    loadDevelopers();
    Swal.fire({
      icon: "success",
      title: "Developer Added!",
      text: `${newDev.name} has been added to the team.`,
      showConfirmButton: false,
      timer: 2000,
      background: "#f0fff4",
      color: "#1b5e20",
      toast: true,
      position: "top-end",
    });
  }
}

// 🌿 Delete developer
async function deleteDeveloper(id, btn) {
  btn.textContent = "…";
  btn.disabled = true;
  const res = await fetch(`${apiUrl}/${id}`, { method: 'DELETE' });
  if (res.ok) {
    btn.closest('.card').remove();
    loadDevelopers();
  }
}

// 🌿 Edit developer
async function editDeveloper(id) {
  const res = await fetch(`${apiUrl}/${id}`);
  const dev = await res.json();

  document.getElementById('name').value = dev.name;
  document.getElementById('title').value = dev.title;
  document.getElementById('skills').value = (dev.skills || []).join(', ');
  document.getElementById('certifications').value = (dev.certifications || []).join(', ');
  form.classList.add("expanded");

  const submitBtn = form.querySelector("button[type='submit']");
  submitBtn.textContent = "Save Changes";

  submitBtn.onclick = async (e) => {
    e.preventDefault();

    const updatedDev = {
      name: document.getElementById('name').value,
      title: document.getElementById('title').value,
      skills: document.getElementById('skills').value.split(',').map(s => s.trim()).filter(Boolean),
      certifications: document.getElementById('certifications').value.split(',').map(c => c.trim()).filter(Boolean)
    };

    const updateRes = await fetch(`${apiUrl}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(updatedDev)
    });

    if (updateRes.ok) {
      Swal.fire({
        icon: "success",
        title: "Updated!",
        text: `${updatedDev.name}'s profile was updated successfully.`,
        showConfirmButton: false,
        timer: 1500,
        background: "#f0fff4",
        color: "#1b5e20",
        toast: true,
        position: "top-end",
      });

      loadDevelopers();
      form.classList.remove("expanded");
      form.reset();
      submitBtn.textContent = "Add Developer";
      submitBtn.onclick = addDeveloper;
    }
  };
}

// 🌿 Sidebar Charts
function updateCharts(data) {
  if (!Array.isArray(data) || data.length === 0) return;

  const skillFreq = {}, roleFreq = {}, certFreq = {};
  data.forEach(dev => {
    const skills = Array.isArray(dev.skills) ? dev.skills : [];
    const certs = Array.isArray(dev.certifications) ? dev.certifications : [];
    const role = dev.title || dev.role || "Unknown";

    skills.forEach(skill => skillFreq[skill] = (skillFreq[skill] || 0) + 1);
    roleFreq[role] = (roleFreq[role] || 0) + 1;
    certs.forEach(cert => certFreq[cert] = (certFreq[cert] || 0) + 1);
  });

  const topSkills = Object.entries(skillFreq).sort((a, b) => b[1] - a[1]).slice(0, 5);
  const topRoles = Object.entries(roleFreq).sort((a, b) => b[1] - a[1]).slice(0, 5);
  const topCerts = Object.entries(certFreq).sort((a, b) => b[1] - a[1]).slice(0, 5);

  const ctxSkills = document.getElementById('skillsChart')?.getContext('2d');
  const ctxRoles = document.getElementById('rolesChart')?.getContext('2d');
  const ctxCerts = document.getElementById('certsChart')?.getContext('2d');
  if (!ctxSkills || !ctxRoles || !ctxCerts) return;

  // Destroy old charts safely
  if (window.skillsChart?.destroy) window.skillsChart.destroy();
  if (window.rolesChart?.destroy) window.rolesChart.destroy();
  if (window.certsChart?.destroy) window.certsChart.destroy();

  const greens = ['#388e3c', '#66bb6a', '#81c784', '#a5d6a7', '#c8e6c9'];

    // 🌱 Top Skills (horizontal bars, clean spacing)
// 🌿 Top Skills (compact + sidebar-optimized)
window.skillsChart = new Chart(ctxSkills, {
  type: 'bar',
  data: {
    labels: topSkills.map(([s]) =>
      s.length > 18 ? s.slice(0, 18) + '…' : s
    ),
    datasets: [{
      data: topSkills.map(([_, v]) => v),
      backgroundColor: ['#388e3c', '#66bb6a', '#81c784', '#a5d6a7', '#c8e6c9'],
      borderRadius: 8,
    }],
  },
  options: {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    layout: {
      padding: { top: 8, bottom: 8, left: 6, right: 6 },
    },
    plugins: {
      legend: { display: false },
      tooltip: {
        titleFont: { size: 11 },
        bodyFont: { size: 10 },
        padding: 6,
      },
    },
    scales: {
      x: {
        grid: { display: false },
        ticks: {
          color: '#1b5e20',
          font: { size: 9, weight: '500' },
          stepSize: 1,
        },
      },
      y: {
        ticks: {
          color: '#1b5e20',
          font: { size: 9, weight: '500' },
          maxRotation: 0,
          autoSkip: false,
        },
      },
    },
  },
});


  // 🌿 Top Roles (animated doughnut, better contrast)
window.rolesChart = new Chart(ctxRoles, {
  type: 'doughnut',
  data: {
    labels: topRoles.map(([r]) => r),
    datasets: [{
      data: topRoles.map(([_, v]) => v),
      backgroundColor: greens,
      borderWidth: 1,
    }],
  },
  options: {
    cutout: '55%',
    animation: { animateRotate: true, duration: 1300 },
    plugins: {
      legend: {
        position: 'bottom',
        labels: {
          boxWidth: 14,
          color: '#1b5e20',
          padding: 10,
          font: { size: 12, weight: '500' },
        },
      },
    },
  },
});


  // 🌸 Top Certifications (horizontal layout, better spacing)
window.certsChart = new Chart(ctxCerts, {
  type: 'bar',
  data: {
    labels: topCerts.map(([c]) =>
      c.length > 18 ? c.slice(0, 18) + '…' : c
    ),
    datasets: [{
      data: topCerts.map(([_, v]) => v),
      backgroundColor: ['#66bb6a', '#81c784', '#a5d6a7', '#c8e6c9', '#e8f5e9'],
      borderRadius: 8,
    }],
  },
  options: {
    indexAxis: 'y',
    responsive: true,
    maintainAspectRatio: false,
    layout: {
      padding: { top: 8, bottom: 8, left: 6, right: 6 },
    },
    plugins: {
      legend: { display: false },
      tooltip: {
        titleFont: { size: 11 },
        bodyFont: { size: 10 },
        padding: 6,
      },
    },
    scales: {
      x: {
        grid: { display: false },
        ticks: {
          color: '#1b5e20',
          font: { size: 9, weight: '500' },
          stepSize: 1,
        },
      },
      y: {
        ticks: {
          color: '#1b5e20',
          font: { size: 9, weight: '500' },
          maxRotation: 0,
          autoSkip: false,
        },
      },
    },
  },
});

}


// ✅ Wait for DOM and Chart.js to be ready before loading everything
document.addEventListener("DOMContentLoaded", async () => {
  console.log("🌱 DOM loaded, initializing DevBase...");

  // Wait until Chart.js is actually available
  if (typeof Chart === "undefined") {
    console.warn("⏳ Waiting for Chart.js to load...");
    await new Promise(resolve => setTimeout(resolve, 500));
  }

  // 1️⃣ Load developers and render stats
  await loadDevelopers();

  // 2️⃣ Ensure canvases have proper dimensions before drawing
  requestAnimationFrame(() => {
    console.log("📊 Forcing chart refresh...");
    const sidebar = document.querySelector(".sidebar");
    if (sidebar) {
      sidebar.style.visibility = "visible";
      sidebar.style.opacity = 1;
    }

    const canvases = document.querySelectorAll("canvas");
    canvases.forEach(c => {
      if (!c.width || !c.height) {
        c.width = 300;
        c.height = 300;
      }
    });
  });
});

// ✅ 3️⃣ Force refresh *after* full page load (final guarantee)
window.addEventListener("load", async () => {
  console.log("💡 Window loaded — forcing chart refresh");
  setTimeout(async () => {
    const res = await fetch('/api/developers');
    const data = await res.json();
    console.log("✅ Retrieved", data.length, "developers for chart refresh");
    updateCharts(data);
  }, 500);
});
