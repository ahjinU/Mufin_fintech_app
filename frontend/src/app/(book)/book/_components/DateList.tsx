const DateList = () => {
  const date = ['일', '월', '화', '수', '목', '금', '토'];

  return (
    <div className="flex flex-row w-full px-[1.2rem] mt-[1.5rem] justify-around text-custom-medium-gray">
      {date?.map((day, index) => {
        return <div key={`date-${index}`}>{day}</div>;
      })}
    </div>
  );
};

export default DateList;
