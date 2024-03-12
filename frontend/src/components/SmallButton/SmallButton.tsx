interface SmallButtonProps {
  mode: 'ACTIVE' | 'NOT_SELECTED' | 'NON_ACTIVE';
  label: string;
  onClick?: () => void;
}

export default function SmallButton({
  mode,
  label,
  ...props
}: SmallButtonProps) {
  let backgroundColor: string;
  let hoverEvent: string;
  switch (mode) {
    case 'ACTIVE':
      backgroundColor = 'bg-custom-purple';
      hoverEvent = 'hover:bg-custom-dark-purple';
      break;
    case 'NOT_SELECTED':
      backgroundColor = 'bg-custom-light-purple';
      hoverEvent = 'hover:bg-custom-purple';
      break;
    case 'NON_ACTIVE':
      backgroundColor = 'bg-custom-medium-gray';
      hoverEvent = '';
      break;
    default:
      backgroundColor = 'bg-custom-medium-gray';
      hoverEvent = '';
  }

  return (
    <button
      className={`w-[14.4rem] h-[3rem] rounded-[0.8rem] text-custom-white custom-medium-text ${backgroundColor} ${hoverEvent}`}
      {...props}
    >
      {label}
    </button>
  );
}
