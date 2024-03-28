interface ButtonProps {
  mode: 'ACTIVE' | 'NOT_SELECTED' | 'NON_ACTIVE';
  label: string;
  onClick?: () => void;
}

export default function Button({
  mode,
  label,
  onClick,
  ...props
}: ButtonProps) {
  let backgroundColor: string;
  switch (mode) {
    case 'ACTIVE':
      backgroundColor = 'bg-custom-purple';
      break;
    case 'NOT_SELECTED':
      backgroundColor = 'bg-custom-light-purple';
      break;
    case 'NON_ACTIVE':
      backgroundColor = 'bg-custom-medium-gray';
      break;
    default:
      backgroundColor = 'bg-custom-medium-gray';
  }

  return (
    <button
      className={`w-full h-[4.4rem] rounded-lg text-custom-white custom-semibold-text ${backgroundColor} ${
        mode === 'ACTIVE' && 'hover:bg-custom-dark-purple'
      }`}
      onClick={onClick}
      {...props}
    >
      {label}
    </button>
  );
}
