interface ButtonProps {
  mode: 'ACTIVE' | 'SELECTED' | 'NOT_SELECTED' | 'NON_ACTIVE';
  label: string;
  onClick?: () => void;
}

export default function Button({ mode, label, ...props }: ButtonProps) {
  let backgroundColor: string;
  let hoverEvent: string;

  switch (mode) {
    case 'ACTIVE':
      backgroundColor = 'bg-custom-purple';
      hoverEvent = 'hover:bg-custom-dark-purple';
      break;
    case 'SELECTED':
      backgroundColor = 'bg-custom-purple';
      hoverEvent = '';
      break;
    case 'NOT_SELECTED':
      backgroundColor = 'bg-custom-light-purple';
      hoverEvent = 'hover:bg-custom-purple';
      break;
    case 'NON_ACTIVE':
    default:
      backgroundColor = 'bg-custom-medium-gray';
      hoverEvent = '';
  }

  return (
    <button
<<<<<<< HEAD
      className={`w-full h-[4.4rem] rounded-lg text-custom-white custom-semibold-text ${backgroundColor} ${
        mode === 'ACTIVE' && 'hover:bg-custom-dark-purple'
      }`}
      onClick={onClick}
=======
      className={`w-full h-[4.4rem] rounded-[0.8rem] text-custom-white custom-semibold-text ${backgroundColor} ${hoverEvent}`}
>>>>>>> e3e51218a0bb264a10f40a804b8df099a9d22c5f
      {...props}
    >
      {label}
    </button>
  );
}
