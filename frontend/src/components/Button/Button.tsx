interface ButtonProps {
  mode: 'ACTIVE' | 'SELECTED' | 'NOT_SELECTED' | 'NON_ACTIVE';
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
      className={`w-full h-[4.4rem] rounded-[0.8rem] text-custom-white custom-semibold-text ${backgroundColor} ${hoverEvent}`}
      {...props}
    >
      {label}
    </button>
  );
}
