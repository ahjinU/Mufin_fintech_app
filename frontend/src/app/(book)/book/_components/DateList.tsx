const DateList = () => {
  const date = ['일', '월', '화', '수', '목', '금', '토'];

  return (
    <div className="flex flex-row w-full px-[1.2rem] mt-[2.5rem] justify-around text-custom-medium-gray mb-[0.5rem]">
      {date?.map((day, index) => {
        return (
          <div key={`date-${index}`}>
            <p className="text-[1.3rem]">{day}</p>
          </div>
        );
      })}
    </div>
  );
};

export default DateList;
